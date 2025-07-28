package cutalab.rachael.base.ui.view;

import java.time.LocalDate;
import java.util.Objects;

import org.slf4j.Logger;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.backend.dto.service.PaymentService;
import cutalab.rachael.backend.dto.wallet.PaymentListRequest;
import cutalab.rachael.backend.model.Payment;
import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.component.dialog.PaymentDialog;
import cutalab.rachael.base.ui.view.costant.PaymentConstant;
import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentDialogType;
import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import cutalab.rachael.util.DateUtils;
import cutalab.rachael.util.LoggerHelper;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "wallet", layout = MainLayout.class)
@PageTitle("Wallet | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class WalletView extends VerticalLayout {

    private static final long serialVersionUID = -2709721999909039188L;

    private final transient PaymentService paymentService;
    private Grid<Payment> grid;
    private ComboBox<PaymentType> paymentTypeCombo;
    private NumberField yearTextField;

	private Span noRecordSpan;
    
    private static final Logger log = LoggerHelper.getLogger(WalletView.class);

    public WalletView(PaymentService paymentService) {
        this.paymentService = paymentService;
        setSizeFull();
        setAlignItems(Alignment.START);
        addClassName(LumoUtility.Padding.MEDIUM);

        add(new ViewToolbar(UIConstant.WALLET_VIEW));

        var addUserButton = new Button(PaymentConstant.PAYMENT_FIELD_ADD_PAYMENT,
                e -> openWalletDialog(PaymentDialogType.CREATE, null));
        addUserButton.getStyle().set("margin-right", "auto");
        addUserButton.setPrefixComponent(VaadinIcon.PLUS.create());
        addUserButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        
        paymentTypeCombo = new ComboBox<PaymentType>();
        paymentTypeCombo.setPlaceholder(PaymentConstant.PAYMENT_FIELD_PAYMENT_TYPE);
        paymentTypeCombo.setWidthFull();
        paymentTypeCombo.setItems(PaymentType.values());
        paymentTypeCombo.setItemLabelGenerator(PaymentType::getLabel);
        
        yearTextField = new NumberField();
        yearTextField.setValue(Double.valueOf(LocalDate.now().getYear()));
        yearTextField.setPlaceholder(PaymentConstant.PAYMENT_FIELD_PAYMENT_YEAR);
        yearTextField.setWidthFull();
        
        paymentTypeCombo.setRequiredIndicatorVisible(true);
        yearTextField.setRequiredIndicatorVisible(true);

        var searchButton = new Button("Cerca", e -> {
            boolean isValid = true;
            PaymentType selectedType = paymentTypeCombo.getValue();
            Double selectedYear = yearTextField.getValue();
            if (selectedType == null) {
                paymentTypeCombo.setInvalid(true);
                paymentTypeCombo.setErrorMessage("Tipo di pagamento obbligatorio");
                isValid = false;
            } else {
                paymentTypeCombo.setInvalid(false);
            }
            if (selectedYear == null || selectedYear < 1900 || selectedYear > 2100) {
                yearTextField.setInvalid(true);
                yearTextField.setErrorMessage("Inserire un anno valido");
                isValid = false;
            } else {
                yearTextField.setInvalid(false);
            }
            if (isValid) {
                fillGrid(selectedType, selectedYear.intValue());
            }
        });
        
        var searchLayout = new HorizontalLayout(addUserButton, paymentTypeCombo, yearTextField, searchButton);
        searchLayout.setWidthFull();
        
        grid = new Grid<>();
        grid.addColumn(Payment::getId).setHeader("ID").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Payment::getAmount).setHeader("Prezzo").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(p -> p.getDueDate() != null ? DateUtils.formatDateInDayMonthYear(p.getDueDate()) : "")
            .setHeader("Data di scadenza").setAutoWidth(true).setFlexGrow(0);
        grid.addComponentColumn((Payment p) -> {
            Icon icon = p.isPaid() ? VaadinIcon.CHECK.create() : VaadinIcon.CLOSE.create();
            icon.getStyle().set("color", p.isPaid() ? "green" : "red");
            icon.getElement().setAttribute("title", p.isPaid() ? "Pagato" : "Non pagato");
            return icon;
        }).setHeader("Pagato").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Payment::getType).setHeader("Tipo di pagamento").setFlexGrow(1);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemDoubleClickListener(e -> {
            Payment selectedPayment = e.getItem();
            if (Objects.nonNull(selectedPayment)) {
                openWalletDialog(PaymentDialogType.UPDATE, selectedPayment);
            }
        });
        grid.setVisible(false);
        add(searchLayout, grid);
        
        //aggiungo e nascondo il messaggio di non aver trovato alcun pagamento
        noRecordSpan = new Span(UIConstant.NO_RECORD_FOUND);
        noRecordSpan.getStyle().set("color", "red");
        noRecordSpan.getStyle().set("font-weight", "bold");
        noRecordSpan.setVisible(false);
        add(noRecordSpan);
    }

    private void fillGrid(PaymentType paymentType, int year) {
        try {
        	var request = new PaymentListRequest();
        	request.setType(paymentType);
        	request.setYear(year);
            var response = paymentService.getAllPayments(request);
            grid.setItems(response.getPayments());
            grid.setVisible(!response.getPayments().isEmpty());
            noRecordSpan.setVisible(response.getPayments().isEmpty());
        } catch (Exception e) {
            Notification.show("Errore nel caricamento dei pagamenti");
        }
    }

    private void openWalletDialog(PaymentDialogType paymentDialogType, Payment payment) {
    	var dialog = new PaymentDialog(paymentDialogType, payment, paymentService,
    			() -> fillGrid(paymentTypeCombo.getValue(), yearTextField.getValue().intValue()));
		dialog.open();
    }
    
}

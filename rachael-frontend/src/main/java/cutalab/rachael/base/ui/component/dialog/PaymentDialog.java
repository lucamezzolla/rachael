package cutalab.rachael.base.ui.component.dialog;

import java.math.BigDecimal;
import java.util.HashMap;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import cutalab.rachael.backend.dto.service.PaymentService;
import cutalab.rachael.backend.dto.wallet.PaymentRequest;
import cutalab.rachael.backend.model.Payment;
import cutalab.rachael.base.ui.view.costant.PaymentConstant;
import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentDialogType;
import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import cutalab.rachael.base.ui.view.costant.UIConstant;

public class PaymentDialog extends Dialog {

	private static final long serialVersionUID = -1786299940955044212L;

	private PaymentDialogType paymentDialogType;
	private final Binder<Payment> binder;
	private final transient PaymentService paymentService;
	private final transient Runnable onSuccess;
	private transient Payment payment;

	private BigDecimalField amountField;
	private DatePicker dateField;
	private DatePicker dueDateField;
	private Checkbox paidField;
	private ComboBox<PaymentType> paymentTypeCombo;

	private VerticalLayout extraDetailsLayout;

	public PaymentDialog(PaymentDialogType paymentDialogType, Payment payment, PaymentService paymentService,
			Runnable onSuccess) {
		this.payment = payment != null ? payment : new Payment();
		if (this.payment.getExtraDetails() == null) {
			this.payment.setExtraDetails(new HashMap<>());
		}
		this.paymentService = paymentService;
		this.paymentDialogType = paymentDialogType;
		this.onSuccess = onSuccess;
		this.binder = new Binder<>(Payment.class);

		setWidth("50%");
		setMaxWidth("50%");
		setMaxHeight("90%");
		setDraggable(true);
		setResizable(false);
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);

		buildUI();
		bindFields();
		binder.readBean(this.payment);
	}

	private void buildUI() {
		setHeaderTitle(paymentDialogType == PaymentDialogType.CREATE
				? PaymentConstant.PAYMENT_TITLE_CREATE
				: PaymentConstant.PAYMENT_TITLE_UPDATE);

		amountField = new BigDecimalField(PaymentConstant.PAYMENT_FIELD_AMOUNT);
		dateField = new DatePicker(PaymentConstant.PAYMENT_FIELD_DATE);
		dueDateField = new DatePicker(PaymentConstant.PAYMENT_FIELD_DUE_DATE);
		paidField = new Checkbox(PaymentConstant.PAYMENT_FIELD_PAID);
		paymentTypeCombo = new ComboBox<>(PaymentConstant.PAYMENT_FIELD_PAYMENT_TYPE);

		amountField.setPrefixComponent(new Span("€"));
		amountField.setValue(BigDecimal.ZERO);
		amountField.setWidthFull();
		dateField.setWidthFull();
		dueDateField.setWidthFull();
		paymentTypeCombo.setWidthFull();
		paymentTypeCombo.setItems(PaymentType.values());
		paymentTypeCombo.setItemLabelGenerator(PaymentType::getLabel);

		var layoutAmountAndDates = new HorizontalLayout(amountField, dateField, dueDateField);
		layoutAmountAndDates.setMargin(false);
		layoutAmountAndDates.setWidthFull();
		
		extraDetailsLayout = new VerticalLayout();
		extraDetailsLayout.setMargin(false);
		extraDetailsLayout.setPadding(false);
		extraDetailsLayout.setWidthFull();
		
		add(paidField, layoutAmountAndDates, paymentTypeCombo, extraDetailsLayout);

		paymentTypeCombo.addValueChangeListener(e -> {
			if (e.getValue() != null) {
				buildExtraFields(e.getValue());
			}
		});

		// In modalità UPDATE: mostra direttamente i campi extra e blocca la selezione
		if (paymentDialogType == PaymentDialogType.UPDATE && payment.getType() != null) {
			paymentTypeCombo.setValue(payment.getType());
			paymentTypeCombo.setReadOnly(true);
			buildExtraFields(payment.getType());
		}

		var cancelButton = new Button(UIConstant.CANCEL, e -> close());
		var submitButton = new Button(UIConstant.SAVE, e -> save());
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		getFooter().add(cancelButton, submitButton);
	}

	private void buildExtraFields(PaymentType type) {
		extraDetailsLayout.removeAll();
		if (type == PaymentType.CONDOMINIO) {
			var extra01 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_NAME);
			var extra02 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_FISCAL_YEAR);
			var extra03 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_RECEIPT_NO);
			var extra04 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_DEBTOR);
			var extra05 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_DESCRIPTION);
			var extra06 = new TextField(PaymentConstant.PAYMENT_FIELD_CONDOMINIUM_IBAN);

			extra01.setWidthFull();
			extra02.setWidthFull();
			extra03.setWidthFull();
			extra04.setWidthFull();
			extra05.setWidthFull();
			extra06.setWidthFull();

			extraDetailsLayout.add(extra01, extra02, extra03, extra04, extra05, extra06);

			var extras = payment.getExtraDetails();

			extra01.setValue((String) extras.getOrDefault("condominiumName", ""));
			extra02.setValue((String) extras.getOrDefault("fiscalYear", ""));
			extra03.setValue((String) extras.getOrDefault("receiptNumber", ""));
			extra04.setValue((String) extras.getOrDefault("debtor", ""));
			extra05.setValue((String) extras.getOrDefault("description", ""));
			extra06.setValue((String) extras.getOrDefault("iban", ""));

			extra01.addValueChangeListener(ev -> extras.put("condominiumName", ev.getValue()));
			extra02.addValueChangeListener(ev -> extras.put("fiscalYear", ev.getValue()));
			extra03.addValueChangeListener(ev -> extras.put("receiptNumber", ev.getValue()));
			extra04.addValueChangeListener(ev -> extras.put("debtor", ev.getValue()));
			extra05.addValueChangeListener(ev -> extras.put("description", ev.getValue()));
			extra06.addValueChangeListener(ev -> extras.put("iban", ev.getValue()));
		}
		if (type == PaymentType.LUCE || type == PaymentType.GAS || type == PaymentType.ACQUA) {
			var extra01 = new TextField(PaymentConstant.PAYMENT_FIELD_LGA_SUPPLIER);
			var extra02 = new TextField(PaymentConstant.PAYMENT_FIELD_LGA_CONSUMPTION);

			extra01.setWidthFull();
			extra02.setWidthFull();

			extraDetailsLayout.add(extra01, extra02);

			var extras = payment.getExtraDetails();

			extra01.setValue((String) extras.getOrDefault("supplier", ""));
			extra02.setValue((String) extras.getOrDefault("consumption", ""));

			extra01.addValueChangeListener(ev -> extras.put("supplier", ev.getValue()));
			extra02.addValueChangeListener(ev -> extras.put("consumption", ev.getValue()));

		}
	}

	private void bindFields() {
		binder.forField(amountField)
				.asRequired("Il prezzo è obbligatorio")
				.bind(Payment::getAmount, Payment::setAmount);

		binder.forField(dateField)
				.asRequired("La data è obbligatoria")
				.bind(Payment::getDate, Payment::setDate);

		binder.forField(dueDateField)
				.bind(Payment::getDueDate, Payment::setDueDate);

		binder.forField(paidField)
				.bind(Payment::isPaid, Payment::setPaid);

		binder.forField(paymentTypeCombo)
				.asRequired("Il tipo di pagamento è obbligatorio")
				.bind(Payment::getType, Payment::setType);
	}

	private void save() {
		if (binder.validate().isOk()) {
			try {
				binder.writeBean(payment);

				PaymentRequest request = new PaymentRequest();
				request.setAmount(payment.getAmount());
				request.setDate(payment.getDate());
				request.setDueDate(payment.getDueDate());
				request.setPaid(payment.isPaid());
				request.setType(payment.getType());

				if (payment.getExtraDetails() != null && !payment.getExtraDetails().isEmpty()) {
					request.setExtraDetails(payment.getExtraDetails());
				}

				if (paymentDialogType == PaymentDialogType.CREATE) {
					paymentService.createPayment(request);
				} else {
					paymentService.updatePayment(payment.getId(), request);
				}

				onSuccess.run();
				close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

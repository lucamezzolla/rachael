package cutalab.rachael.base.ui.component.dialog;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import cutalab.rachael.backend.dto.album.DiskDTO;
import cutalab.rachael.backend.dto.album.DiskRequestDTO;
import cutalab.rachael.backend.dto.album.DiskStatusDTO;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.util.SessionUtil;
import cutalab.rachael.base.ui.view.costant.AlbumConstant;
import cutalab.rachael.base.ui.view.costant.AlbumConstant.AlbumDialogType;
import cutalab.rachael.base.ui.view.costant.UIConstant;

public class AlbumDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    private final Binder<DiskDTO> binder;
    private final DiskService diskService;
    private final AlbumDialogType dialogType;
    private final Runnable onSuccess;
    private DiskDTO disk;

    private TextField titleField;
    private TextField authorField;
    private TextField labelField;
    private TextField yearField;
    private TextField reprintField;
    private Checkbox openableField;
    private BigDecimalField valueField;
    private ComboBox<DiskStatusDTO> diskStatusCombo;
    private ComboBox<DiskStatusDTO> coverStatusCombo;
    private TextArea noteArea;

    public AlbumDialog(AlbumDialogType dialogType, DiskDTO disk, DiskService diskService, Runnable onSuccess) {
        this.disk = disk != null ? disk : new DiskDTO();
        if (this.disk.getGenres() == null) this.disk.setGenres(new ArrayList<>());
        if (this.disk.getStyles() == null) this.disk.setStyles(new ArrayList<>());

        this.diskService = diskService;
        this.dialogType = dialogType;
        this.onSuccess = onSuccess;
        this.binder = new Binder<>(DiskDTO.class);

        setWidth("600px");
        setMaxHeight("90%");
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);

        buildUI();
        bindFields();
        binder.readBean(this.disk);
    }

    private void buildUI() {
        setHeaderTitle(dialogType == AlbumDialogType.CREATE
            ? AlbumConstant.DISK_TITLE_CREATE
            : AlbumConstant.DISK_TITLE_UPDATE);

        titleField = new TextField(AlbumConstant.DISK_FIELD_TITLE);
        authorField = new TextField(AlbumConstant.DISK_FIELD_AUTHOR);
        labelField = new TextField(AlbumConstant.DISK_FIELD_LABEL);
        yearField = new TextField(AlbumConstant.DISK_FIELD_YEAR);
        reprintField = new TextField(AlbumConstant.DISK_FIELD_REPRINT);
        openableField = new Checkbox(AlbumConstant.DISK_FIELD_OPENABLE);
        valueField = new BigDecimalField(AlbumConstant.DISK_FIELD_VALUE);
        valueField.setPrefixComponent(new Span("€"));
        
        titleField.setWidthFull(); authorField.setWidthFull(); labelField.setWidthFull();
        yearField.setWidthFull(); reprintField.setWidthFull();  valueField.setWidthFull();

        diskStatusCombo = new ComboBox<>(AlbumConstant.DISK_FIELD_DISK_STATUS);
        coverStatusCombo = new ComboBox<>(AlbumConstant.DISK_FIELD_COVER_STATUS);

        List<DiskStatusDTO> statuses = diskService.getAllStatuses();

        diskStatusCombo.setItems(statuses);
        coverStatusCombo.setItems(statuses);

        diskStatusCombo.setItemLabelGenerator(DiskStatusDTO::getName);
        coverStatusCombo.setItemLabelGenerator(DiskStatusDTO::getName);

        
        diskStatusCombo.setWidthFull(); coverStatusCombo.setWidthFull();
        
        // dovrebbero essere riempiti con valori disponibili nel tuo dominio

        noteArea = new TextArea(AlbumConstant.DISK_FIELD_NOTE);
        noteArea.setWidthFull();
        
        var intLayout01 = new HorizontalLayout(diskStatusCombo, coverStatusCombo);
        intLayout01.setWidthFull();

        VerticalLayout main = new VerticalLayout(
            titleField, authorField, labelField, yearField,
            reprintField, openableField, valueField,
            intLayout01,
            noteArea
        );
        main.setPadding(false);
        main.setSpacing(true);

        add(main);

        Button cancelButton = new Button(UIConstant.CANCEL, e -> close());
        Button saveButton = new Button(UIConstant.SAVE, e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getFooter().add(new HorizontalLayout(cancelButton, saveButton));
    }

    private void bindFields() {
        binder.forField(titleField)
            .asRequired(AlbumConstant.DISK_VALIDATION_TITLE)
            .bind(DiskDTO::getTitle, DiskDTO::setTitle);

        binder.forField(authorField)
            .asRequired(AlbumConstant.DISK_VALIDATION_AUTHOR)
            .bind(DiskDTO::getAuthor, DiskDTO::setAuthor);

        binder.forField(labelField)
            .bind(DiskDTO::getLabel, DiskDTO::setLabel);

        binder.forField(yearField)
            .bind(DiskDTO::getYear, DiskDTO::setYear);

        binder.forField(reprintField)
            .bind(DiskDTO::getReprint, DiskDTO::setReprint);

        binder.forField(openableField)
            .bind(DiskDTO::isOpenable, DiskDTO::setOpenable);

        binder.forField(valueField)
            .bind(DiskDTO::getPresumedValue, DiskDTO::setPresumedValue);

        binder.forField(diskStatusCombo)
        .asRequired(AlbumConstant.DISK_VALIDATION_STATUS)
        .bind(DiskDTO::getDiskStatus, DiskDTO::setDiskStatus);

    binder.forField(coverStatusCombo)
    	.asRequired(AlbumConstant.DISK_VALIDATION_STATUS)
        .bind(DiskDTO::getCoverStatus, DiskDTO::setCoverStatus);


        binder.forField(noteArea)
            .bind(DiskDTO::getNote, DiskDTO::setNote);
    }

    private void save() {
        if (binder.validate().isOk()) {
            try {
                binder.writeBean(disk);
                DiskRequestDTO request = toRequest(disk);

                if (dialogType == AlbumDialogType.CREATE) {
                    diskService.createDisk(request);
                } else {
                    diskService.updateDisk(disk.getId(), request);
                }
                onSuccess.run();
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private DiskRequestDTO toRequest(DiskDTO disk) {
        DiskRequestDTO request = new DiskRequestDTO();
        request.setAuthor(disk.getAuthor());
        request.setCover(disk.getCover());
        request.setLabel(disk.getLabel());
        request.setNote(disk.getNote());
        request.setOpenable(disk.isOpenable());
        request.setPresumedValue(disk.getPresumedValue());
        request.setReprint(disk.getReprint());
        request.setTitle(disk.getTitle());
        request.setYear(disk.getYear());
        request.setCoverStatus(disk.getCoverStatus());
        request.setDiskStatus(disk.getDiskStatus());
        request.setGenres(disk.getGenres());
        request.setStyles(disk.getStyles());
        request.setUserId(Long.valueOf(SessionUtil.getCurrentUser().getId()));
        return request;
    }

}
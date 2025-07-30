package cutalab.rachael.base.ui.component.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import cutalab.rachael.backend.dto.album.DiskDTO;
import cutalab.rachael.backend.dto.album.DiskGenreDTO;
import cutalab.rachael.backend.dto.album.DiskRequestDTO;
import cutalab.rachael.backend.dto.album.DiskStatusDTO;
import cutalab.rachael.backend.dto.album.DiskStyleDTO;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.util.NotificationUtil;
import cutalab.rachael.base.ui.util.NotificationUtil.Duration;
import cutalab.rachael.base.ui.util.NotificationUtil.Type;
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
    private ComboBox<String> reprintField;
    private Checkbox openableField;
    private BigDecimalField valueField;
    private ComboBox<DiskStatusDTO> diskStatusCombo;
    private ComboBox<DiskStatusDTO> coverStatusCombo;
    private TextArea noteArea;

	private MultiSelectComboBox<DiskGenreDTO> genreMultiSelect;

	private MultiSelectComboBox<DiskStyleDTO> styleMultiSelect;

    public AlbumDialog(AlbumDialogType dialogType, DiskDTO disk, DiskService diskService, Runnable onSuccess) {
        this.disk = disk != null ? disk : new DiskDTO();
        if (this.disk.getGenres() == null) this.disk.setGenres(new ArrayList<>());
        if (this.disk.getStyles() == null) this.disk.setStyles(new ArrayList<>());

        this.diskService = diskService;
        this.dialogType = dialogType;
        this.onSuccess = onSuccess;
        this.binder = new Binder<>(DiskDTO.class);

        setWidth("80%");
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
        reprintField = new ComboBox<>(AlbumConstant.DISK_FIELD_REPRINT);
        openableField = new Checkbox(AlbumConstant.DISK_FIELD_OPENABLE);
        valueField = new BigDecimalField(AlbumConstant.DISK_FIELD_VALUE);
        valueField.setPrefixComponent(new Span("€"));
        
        openableField.getStyle().set("margin-top", "10px");
        
        titleField.setWidthFull(); authorField.setWidthFull(); labelField.setWidthFull();
        yearField.setWidthFull(); reprintField.setWidthFull();  valueField.setWidthFull();

        diskStatusCombo = new ComboBox<>(AlbumConstant.DISK_FIELD_DISK_STATUS);
        coverStatusCombo = new ComboBox<>(AlbumConstant.DISK_FIELD_COVER_STATUS);
        
        reprintField.setItems("sì", "no");

        List<DiskStatusDTO> statuses = diskService.getAllStatuses();

        diskStatusCombo.setItems(statuses);
        coverStatusCombo.setItems(statuses);

        diskStatusCombo.setItemLabelGenerator(DiskStatusDTO::getName);
        coverStatusCombo.setItemLabelGenerator(DiskStatusDTO::getName);

        
        diskStatusCombo.setWidthFull(); coverStatusCombo.setWidthFull();
        
        // dovrebbero essere riempiti con valori disponibili nel tuo dominio

        noteArea = new TextArea(AlbumConstant.DISK_FIELD_NOTE);
        noteArea.setWidthFull();
        
        genreMultiSelect = buildGenreList();
        styleMultiSelect = buildStyleList();
        
        var intLayout01 = new HorizontalLayout(diskStatusCombo, coverStatusCombo);
        intLayout01.setWidthFull();
        
        var intLayout02 = new HorizontalLayout(yearField, reprintField);
        intLayout02.setWidthFull();
        
        HorizontalLayout hl = new HorizontalLayout();
        hl.setMargin(false);
        hl.setSizeFull();

        VerticalLayout vl = new VerticalLayout(
            titleField,
            genreMultiSelect,
            labelField, 
            intLayout02,
            intLayout01
        );
        vl.setPadding(false);
        vl.setSpacing(true);
        
        VerticalLayout vl2 = new VerticalLayout(
        	authorField, 
    		styleMultiSelect,
    		valueField,
    		noteArea,
    		openableField
		);
        vl2.setPadding(false);
        vl2.setSpacing(true);
        vl2.setMargin(false);
        
        hl.add(vl, vl2);
        add(hl);

        Button cancelButton = new Button(UIConstant.CANCEL, e -> close());
        Button saveButton = new Button(UIConstant.SAVE, e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getFooter().add(new HorizontalLayout(cancelButton, saveButton));
    }

    private void bindFields() {
        binder.forField(titleField)
            .asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
            .bind(DiskDTO::getTitle, DiskDTO::setTitle);

        binder.forField(authorField)
            .asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
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
        .asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
        .bind(DiskDTO::getDiskStatus, DiskDTO::setDiskStatus);

        binder.forField(coverStatusCombo)
    	.asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
        .bind(DiskDTO::getCoverStatus, DiskDTO::setCoverStatus);
        
        binder.forField(genreMultiSelect)
        .asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
        .bind(
            dto -> new HashSet<>(dto.getGenres()),
            (dto, selected) -> dto.setGenres(new ArrayList<>(selected))
        );

        binder.forField(styleMultiSelect)
        .asRequired(AlbumConstant.DISK_VALIDATION_FIELD)
        .bind(
            dto -> new HashSet<>(dto.getStyles()),
            (dto, selected) -> dto.setStyles(new ArrayList<>(selected))
        );

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
                NotificationUtil.show(UIConstant.SAVED, Duration.FAST, Type.SUCCESS);
                onSuccess.run();
                close();
            } catch (Exception e) {
            	NotificationUtil.show(UIConstant.ERROR, Duration.FAST, Type.ERROR);
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
    
    private MultiSelectComboBox<DiskGenreDTO> buildGenreList() {
        var allGenres = diskService.getAllGenres().getGenres();
        MultiSelectComboBox<DiskGenreDTO> listBox = new MultiSelectComboBox<>(AlbumConstant.ADD_GENRE);
        listBox.setItemLabelGenerator(DiskGenreDTO::getName);
        listBox.setWidthFull();
        listBox.setItems(allGenres);
        if(Objects.nonNull(disk.getGenres())) {
        	listBox.select(disk.getGenres());
        }  
        return listBox;
    }

    private MultiSelectComboBox<DiskStyleDTO> buildStyleList() {
        var allStyles = diskService.getAllStyles().getStyles();
        MultiSelectComboBox<DiskStyleDTO> listBox = new MultiSelectComboBox<>(AlbumConstant.ADD_STYLE);
        listBox.setItemLabelGenerator(DiskStyleDTO::getName);
        listBox.setWidthFull();
        listBox.setItems(allStyles);
        if(Objects.nonNull(disk.getStyles())) {
        	listBox.select(disk.getStyles());
        }
        return listBox;
    }

}
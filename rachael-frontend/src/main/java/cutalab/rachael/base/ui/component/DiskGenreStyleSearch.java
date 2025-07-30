package cutalab.rachael.base.ui.component;

import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import cutalab.rachael.backend.dto.album.DiskGenreDTO;
import cutalab.rachael.backend.dto.album.DiskStyleDTO;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.view.costant.AlbumConstant.AlbumGenreStyleType;

public class DiskGenreStyleSearch extends VerticalLayout {

    private static final long serialVersionUID = -4147115921166036867L;

    private final AlbumGenreStyleType type;
    private final DiskService diskService;

    public DiskGenreStyleSearch(AlbumGenreStyleType type, DiskService diskService) {
        this.type = type;
        this.diskService = diskService;
        setSizeFull();
        buildUI();
    }

    private void buildUI() {
        if (type == AlbumGenreStyleType.GENRE) {
        	buildGenreList();
        } else {
        	buildStyleList();
        }
    }

    private void buildGenreList() {
        var allGenres = diskService.getAllGenres().getGenres();

        TextField filterField = new TextField("Filtra generi");
        filterField.setPlaceholder("Scrivi per cercare...");
        filterField.setClearButtonVisible(true);
        filterField.setWidthFull();

        MultiSelectListBox<DiskGenreDTO> listBox = new MultiSelectListBox<>();
        listBox.setItemLabelGenerator(DiskGenreDTO::getName);
        listBox.setWidthFull();

        // Mostra inizialmente tutti
        listBox.setItems(allGenres);

        // Filtro dinamico
        filterField.addValueChangeListener(event -> {
            String filter = event.getValue().toLowerCase();
            var filtered = allGenres.stream()
                .filter(g -> g.getName().toLowerCase().contains(filter))
                .toList();
            listBox.setItems(filtered);
        });

        add(filterField, listBox);
    }


    private void buildStyleList() {
        var allStyles = diskService.getAllStyles().getStyles();

        TextField filterField = new TextField("Filtra stili");
        filterField.setPlaceholder("Scrivi per cercare...");
        filterField.setClearButtonVisible(true);
        filterField.setWidthFull();

        MultiSelectListBox<DiskStyleDTO> listBox = new MultiSelectListBox<>();
        listBox.setItemLabelGenerator(DiskStyleDTO::getName);
        listBox.setWidthFull();

        listBox.setItems(allStyles);

        filterField.addValueChangeListener(event -> {
            String filter = event.getValue().toLowerCase();
            var filtered = allStyles.stream()
                .filter(s -> s.getName().toLowerCase().contains(filter))
                .toList();
            listBox.setItems(filtered);
        });

        add(filterField, listBox);
    }

}

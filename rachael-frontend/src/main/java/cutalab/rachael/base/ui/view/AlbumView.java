package cutalab.rachael.base.ui.view;

import java.util.Objects;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.backend.dto.album.DiskDTO;
import cutalab.rachael.backend.dto.album.DiskListRequestDTO;
import cutalab.rachael.backend.dto.album.DiskListResponseDTO;
import cutalab.rachael.backend.dto.album.DiskResponseDTO;
import cutalab.rachael.backend.dto.album.DiskSummaryDTO;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.component.dialog.AlbumDialog;
import cutalab.rachael.base.ui.util.SessionUtil;
import cutalab.rachael.base.ui.view.costant.AlbumConstant;
import cutalab.rachael.base.ui.view.costant.AlbumConstant.AlbumDialogType;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "album", layout = MainLayout.class)
@PageTitle("Album | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class AlbumView extends VerticalLayout {
	
	private static final long serialVersionUID = -4533361550229762771L;
	
	private DiskService diskService;
	private Grid<DiskSummaryDTO> grid;

	public AlbumView(DiskService diskService) {
		this.diskService = diskService;
		setSizeFull();
        setAlignItems(Alignment.START);
        addClassName(LumoUtility.Padding.MEDIUM);

        add(new ViewToolbar(UIConstant.ALBUM_VIEW));
        var addUDiskButton = new Button(AlbumConstant.DISK_FIELD_ADD_USER, e -> openDiskDialog(AlbumDialogType.CREATE, null));
        addUDiskButton.getStyle().set("margin-right", "auto");
        addUDiskButton.setPrefixComponent(VaadinIcon.PLUS.create());
        addUDiskButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        grid = new Grid<>();
        grid.addColumn(DiskSummaryDTO::getId)
	        .setHeader("ID")
	        .setAutoWidth(true)
	        .setFlexGrow(0);
	    grid.addColumn(DiskSummaryDTO::getTitle)
	        .setHeader(AlbumConstant.DISK_FIELD_TITLE)
	        .setFlexGrow(1);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemDoubleClickListener(e -> {
        	DiskSummaryDTO selectedDisk = e.getItem();
        	if (Objects.nonNull(selectedDisk)) {
        		var response = diskService.getDiskById(selectedDisk.getId());
        		openDiskDialog(AlbumDialogType.UPDATE, toDiskDTO(response));
        	}
        });
        add(addUDiskButton, grid);
        fillGrid();
    }
	
	private void openDiskDialog(AlbumDialogType type, DiskDTO disk) {
		AlbumDialog dialog = new AlbumDialog(type, disk, diskService, this::fillGrid);
		dialog.open();
	}
	
	private void fillGrid() {
		DiskListRequestDTO request = new DiskListRequestDTO();
		request.setUserId(SessionUtil.getCurrentUser().getId());
        DiskListResponseDTO response = diskService.getAllDisks(request);
        grid.setItems(response.getDisks());
	}
	
	private DiskDTO toDiskDTO(DiskResponseDTO response) {
		DiskDTO dto = new DiskDTO();
		dto.setId(response.getId());
		dto.setTitle(response.getTitle());
		dto.setAuthor(response.getAuthor());
		dto.setLabel(response.getLabel());
		dto.setYear(response.getYear());
		dto.setReprint(response.getReprint());
		dto.setNote(response.getNote());
		dto.setCover(response.getCover());
		dto.setOpenable(response.isOpenable());
		dto.setPresumedValue(response.getPresumedValue());
		dto.setCoverStatus(response.getCoverStatus());
		dto.setDiskStatus(response.getDiskStatus());
		dto.setGenres(response.getGenres());
		dto.setStyles(response.getStyles());
		dto.setUserId(response.getUserId());
		return dto;
	}

	
}

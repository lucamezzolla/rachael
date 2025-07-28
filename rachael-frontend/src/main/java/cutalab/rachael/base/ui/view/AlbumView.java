package cutalab.rachael.base.ui.view;

import java.util.Objects;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.backend.dto.album.DiskListRequest;
import cutalab.rachael.backend.dto.album.DiskListResponse;
import cutalab.rachael.backend.dto.album.DiskSummary;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.util.SessionUtil;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "album", layout = MainLayout.class)
@PageTitle("Album | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class AlbumView extends VerticalLayout {
	
	private static final long serialVersionUID = -4533361550229762771L;
	
	private DiskService diskService;
	private Grid<DiskSummary> grid;

	public AlbumView(DiskService diskService) {
		this.diskService = diskService;
		setSizeFull();
        setAlignItems(Alignment.START);
        addClassName(LumoUtility.Padding.MEDIUM);

        add(new ViewToolbar(UIConstant.ALBUM_VIEW));
        // var addUserButton = new Button(UserConstant.USER_FIELD_ADD_USER, e -> openUserDialog(UserDialogType.CREATE, null));
//        addUserButton.getStyle().set("margin-right", "auto");
//        addUserButton.setPrefixComponent(VaadinIcon.PLUS.create());
//        addUserButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        grid = new Grid<>();
        grid.addColumn(DiskSummary::getId)
	        .setHeader("ID")
	        .setAutoWidth(true)
	        .setFlexGrow(0);
	    grid.addColumn(DiskSummary::getTitle)
	        .setHeader("Titolo")
	        .setFlexGrow(1);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemDoubleClickListener(e -> {
        	DiskSummary selectedDisk = e.getItem();
        	if(Objects.nonNull(selectedDisk)) {
        		
        	}
        });
        add(grid);
        fillGrid();
    }
	
//	private void openUserDialog(UserDialogType type, User user) {
//		//
//	}
	
	private void fillGrid() {
		DiskListRequest request = new DiskListRequest();
		request.setUserId(SessionUtil.getCurrentUser().getId());
        DiskListResponse response = diskService.getAllDisks(request);
        grid.setItems(response.getDisks());
	}
	
}

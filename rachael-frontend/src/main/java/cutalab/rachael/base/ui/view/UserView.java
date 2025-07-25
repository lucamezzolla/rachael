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

import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.dto.user.UserListResponse;
import cutalab.rachael.backend.model.User;
import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.component.dialog.UserDialog;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import cutalab.rachael.base.ui.view.costant.UserConstant;
import cutalab.rachael.base.ui.view.costant.UserConstant.UserDialogType;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "users", layout = MainLayout.class)
@PageTitle("Utenti | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class UserView extends VerticalLayout {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1447885134423935935L;
	
	private UserService userService;
	private Grid<User> grid;

	public UserView(UserService userService) {
		this.userService = userService;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        addClassName(LumoUtility.Padding.MEDIUM);
        add(new ViewToolbar(UIConstant.USER_VIEW));
        var addUserButton = new Button(UserConstant.USER_FIELD_ADD_USER, e -> openUserDialog(UserDialogType.CREATE, null));
        addUserButton.getStyle().set("margin-right", "auto");
        addUserButton.setPrefixComponent(VaadinIcon.PLUS.create());
        addUserButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        grid = new Grid<>();
        grid.addColumn(User::getId)
	        .setHeader("ID")
	        .setAutoWidth(true)
	        .setFlexGrow(0);
	    grid.addColumn(User::getName)
	        .setHeader("Nome")
	        .setFlexGrow(1);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemDoubleClickListener(e -> {
        	User selectedUser = e.getItem();
        	if(Objects.nonNull(selectedUser)) {
        		User user = userService.getUserById(selectedUser.getId());
        		if(Objects.nonNull(user)) {
        			openUserDialog(UserDialogType.UPDATE, user);
        		}
        	}
        });
        add(addUserButton, grid);
        fillGrid();
    }
	
	private void openUserDialog(UserDialogType type, User user) {
		var dialog = new UserDialog(type, user, userService, this::fillGrid);
		dialog.open();
	}
	
	private void fillGrid() {	 
        UserListResponse response = userService.getAllUsers();
        grid.setItems(response.getUsers());
	}
	
}
package cutalab.rachael.base.ui.component.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;

import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.dto.user.UserRequest;
import cutalab.rachael.backend.dto.user.UserResponse;
import cutalab.rachael.backend.dto.user.UserUpdateRequest;
import cutalab.rachael.backend.model.User;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import cutalab.rachael.base.ui.view.costant.UserConstant;
import cutalab.rachael.base.ui.view.costant.UserConstant.UserDialogType;

public class UserDialog extends Dialog {

	private static final long serialVersionUID = -6704288331170633118L;

	private final UserDialogType userDialogType;
	private final Binder<User> binder;
	private final UserService userService;
	private transient User user;

	private TextField nameTextField, emailTextField;
	private PasswordField passwordField;
	private Button cancelButton, submitButton, changePasswordButton;
	
	private final Runnable onSuccess;

	public UserDialog(UserDialogType userDialogType, User user, UserService userService, Runnable onSuccess) {
	    this.userDialogType = userDialogType;
	    this.user = (user != null) ? user : new User();
	    this.userService = userService;
	    this.onSuccess = onSuccess;
	    this.binder = new Binder<>(User.class);
	    setWidth("50%");
		setMaxWidth("50%");
		setMaxHeight("90%");
		setDraggable(true);
		setResizable(false);
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);
	    buildUI();
	    bindFields();
	    binder.readBean(this.user);
	}

	private void buildUI() {
		setHeaderTitle(
			userDialogType == UserDialogType.CREATE 
			? UserConstant.USER_TITLE_CREATE 
			: UserConstant.USER_TITLE_UPDATE
		);

		nameTextField = new TextField(UserConstant.USER_FIELD_NAME);
		emailTextField = new TextField(UserConstant.USER_FIELD_EMAIL);
		nameTextField.setWidthFull();
		emailTextField.setWidthFull();
		nameTextField.getElement().setAttribute("autocomplete", "off");
		emailTextField.getElement().setAttribute("autocomplete", "off");

		cancelButton = new Button(UIConstant.CANCEL, e -> close());
		submitButton = new Button(UIConstant.SAVE, e -> save());
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		add(nameTextField, emailTextField);

		if (userDialogType == UserDialogType.CREATE) {
			passwordField = new PasswordField(UserConstant.USER_FIELD_PASSWORD);
			passwordField.setWidthFull();
			passwordField.getElement().setAttribute("autocomplete", "new-password");
			add(passwordField);
			getFooter().add(cancelButton, submitButton);
		} else {
			changePasswordButton = new Button(UserConstant.USER_FIELD_CHANGE_PASSWORD, e -> openChangePasswordDialog());
			nameTextField.setEnabled(false);
			getFooter().add(cancelButton, changePasswordButton, submitButton);
		}
	}
	
	private void openChangePasswordDialog() {
	    var dialog = new ChangePasswordDialog(user.getId(), userService);
	    dialog.open();
	}

	private void bindFields() {
		binder.forField(nameTextField)
			.asRequired("Il nome è obbligatorio")
			.bind(User::getName, User::setName);

		binder.forField(emailTextField)
			.asRequired("L'email è obbligatoria")
			.withValidator(new EmailValidator("Email non valida"))
			.bind(User::getEmail, User::setEmail);

		if (userDialogType == UserDialogType.CREATE) {
			binder.forField(passwordField)
				.asRequired("La password è obbligatoria")
				.bind(User::getPassword, User::setPassword);
		}
	}

	private void save() {
		if (binder.validate().isOk()) {
			try {
				binder.writeBean(this.user);
				if (userDialogType == UserDialogType.CREATE) {
					UserRequest request = new UserRequest();
					request.setName(user.getName());
					request.setEmail(user.getEmail());
					request.setPassword(user.getPassword());
					UserResponse response = userService.createUser(request);
					Notification.show(response.getMessage(), 3000, Position.TOP_CENTER);
				} else {
					UserUpdateRequest request = new UserUpdateRequest();
					request.setName(user.getName());
					request.setEmail(user.getEmail());
					UserResponse response = userService.updateUser(user.getId(), request);
					Notification.show(response.getMessage(), 3000, Position.TOP_CENTER);
				}
				close();
				if (onSuccess != null) {
				    onSuccess.run();
				}
			} catch (ValidationException e) {
				Notification.show("Errore di validazione", 3000, Position.TOP_CENTER);
			}
		}
	}
	
}
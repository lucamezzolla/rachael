package cutalab.rachael.base.ui.component.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.dto.user.ChangePasswordRequest;
import cutalab.rachael.base.ui.util.NotificationUtil;
import cutalab.rachael.base.ui.util.NotificationUtil.Duration;
import cutalab.rachael.base.ui.util.NotificationUtil.Type;
import cutalab.rachael.base.ui.view.costant.UserConstant;
import cutalab.rachael.base.ui.view.costant.ValidationConstant;
import cutalab.rachael.base.ui.view.costant.UIConstant;

public class ChangePasswordDialog extends Dialog {

    private static final long serialVersionUID = -91692452056562825L;
    
	private final PasswordField newPasswordField = new PasswordField(UserConstant.USER_FIELD_NEW_PASSWORD);
    private final PasswordField confirmPasswordField = new PasswordField(UserConstant.USER_FIELD_CONFIRM_PASSWORD);
    private final Button cancelButton = new Button(UIConstant.CANCEL, e -> close());
    private final Button saveButton = new Button(UIConstant.SAVE);

    private final Binder<ChangePasswordRequest> binder = new Binder<>(ChangePasswordRequest.class);
    private final UserService userService;

    public ChangePasswordDialog(Long userId, UserService userService) {
        this.userService = userService;

        setHeaderTitle(UserConstant.USER_TITLE_CHANGE_PASSWORD);
        setWidth("400px");
        setDraggable(true);
        setResizable(false);
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);

        newPasswordField.setWidthFull();
        confirmPasswordField.setWidthFull();
        newPasswordField.getElement().setAttribute("autocomplete", "new-password");
        confirmPasswordField.getElement().setAttribute("autocomplete", "new-password");

        binder.forField(newPasswordField)
            .asRequired(UserConstant.VALIDATION_PASSWORD_REQUIRED)
            .withValidator(new StringLengthValidator(UserConstant.VALIDATION_PASSWORD_TOO_SHORT, 6, null))
            .bind(ChangePasswordRequest::getPassword, ChangePasswordRequest::setPassword);

        binder.forField(confirmPasswordField)
            .asRequired(UserConstant.VALIDATION_CONFIRM_REQUIRED)
            .withValidator(value -> value.equals(newPasswordField.getValue()), UserConstant.VALIDATION_PASSWORD_MISMATCH)
            .bind(req -> newPasswordField.getValue(), (req, val) -> {});

        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(e -> savePassword(userId));

        add(newPasswordField, confirmPasswordField);
        getFooter().add(cancelButton, saveButton);
    }

    private void savePassword(Long userId) {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setPassword(newPasswordField.getValue());
        try {
            binder.writeBean(request);
            GenericResponse response = userService.changePassword(userId, request);
            NotificationUtil.show(response.getMessage(), Duration.FAST, Type.SUCCESS);
            close();
        } catch (ValidationException e) {
            NotificationUtil.show(ValidationConstant.VALIDATION_ERROR, Duration.FAST, Type.ERROR);
        }
    }
    
}
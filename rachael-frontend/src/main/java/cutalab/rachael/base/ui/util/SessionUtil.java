package cutalab.rachael.base.ui.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import cutalab.rachael.backend.model.User;

public class SessionUtil {

    private static final String USER_SESSION_KEY = "loggedUser";

    public static void setCurrentUser(User user) {
        VaadinSession.getCurrent().setAttribute(USER_SESSION_KEY, user);
    }

    public static User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(USER_SESSION_KEY);
    }

    public static boolean isUserLoggedIn() {
        return getCurrentUser() != null;
    }

    public static void logout() {
    	VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().setLocation("login");
    }
    
}
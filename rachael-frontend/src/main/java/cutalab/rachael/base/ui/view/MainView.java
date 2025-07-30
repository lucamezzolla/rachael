package cutalab.rachael.base.ui.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.base.ui.component.ViewToolbar;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Home | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public final class MainView extends VerticalLayout implements BeforeEnterObserver {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3659409287538821345L;
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean unauthenticated = auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal());
	    if (unauthenticated) {
	        event.forwardTo(LoginView.class);
	    }
	}


	MainView() {
		setSizeFull();
        setAlignItems(Alignment.START);
        addClassName(LumoUtility.Padding.MEDIUM);
        add(new ViewToolbar("Home Page"));
        Image img = new Image("images/tyrell_home.png", "Rachael");
        img.getStyle()
           .set("width", "100%")
           .set("border", "1px black solid")
           .set("object-fit", "cover");
        Button newsButton = new Button("Ultime novità");
        newsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(img, newsButton);
        setHorizontalComponentAlignment(Alignment.CENTER, img, newsButton);
    }

    /**
     * Navigates to the main view.
     */
    public static void showMainView() {
        UI.getCurrent().navigate(MainView.class);
    }

}
package cutalab.rachael.base.ui.view;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.dto.user.UserLoginRequest;
import cutalab.rachael.backend.dto.user.UserResponse;
import cutalab.rachael.backend.model.User;
import cutalab.rachael.base.ui.util.SessionUtil;
import cutalab.rachael.util.LoggerHelper;
import jakarta.servlet.http.HttpServletRequest;

@Route(value = "login", layout = EmptyLayout.class)
@PageTitle("Login | Rachael App")
@AnonymousAllowed
@PreserveOnRefresh
public class LoginView extends VerticalLayout {

    private static final long serialVersionUID = -3807929377313748417L;

	private static final Logger log = LoggerHelper.getLogger(LoginView.class);

    private final TextField email = new TextField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final Button loginButton = new Button("Login");

    @Autowired
    private UserService userService;
    
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            UI.getCurrent().navigate(MainView.class);
        }
    }

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSpacing(true);

        H1 title = new H1("Rachael Tyrell");
        H2 subtitle = new H2("Cuta Laboratories Server");

        Image img = new Image("images/rachael_01.jpg", "Rachael");
        img.setMaxHeight("300px");
        img.getStyle()
            .set("border-radius", "50%")
            .set("margin-top", "1.5rem")
            .set("margin-bottom", "1.5rem")
            .set("border", "1px solid black");

        email.setWidth("300px");
        password.setWidth("300px");
        loginButton.setWidth("300px");

        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginButton.addClickShortcut(Key.ENTER);
        loginButton.addClickListener(e -> handleLogin());

        add(title, subtitle, img, email, password, loginButton);
    }

    private void handleLogin() {
        try {
            UserLoginRequest request = new UserLoginRequest();
            request.setEmail(email.getValue());
            request.setPassword(password.getValue());

            UserResponse response = userService.login(request);

            if (response != null && response.getToken() != null) {
                User user = new User();
                user.setId(response.getId());
                user.setEmail(response.getEmail());
                user.setName(response.getName());
                user.setToken(response.getToken());
                SessionUtil.setCurrentUser(user);

                List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_USER"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), null, authorities
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                HttpServletRequest servletRequest =
                    ((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest();
                servletRequest.getSession().setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
                );

                log.info("Login riuscito per {}", user.getEmail());
                UI.getCurrent().navigate(MainView.class);


            } else {
                log.warn("Login fallito: credenziali errate");
                Notification.show("Credenziali errate").setDuration(3000);
            }

        } catch (Exception ex) {
            log.error("Errore durante il login", ex);
            Notification.show("Errore durante il login").setDuration(3000);
        }
    }
}

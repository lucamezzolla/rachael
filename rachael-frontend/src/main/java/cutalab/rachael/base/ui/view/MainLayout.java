package cutalab.rachael.base.ui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.IconSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

import cutalab.rachael.backend.dto.service.PaymentService;
import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.model.User;
import cutalab.rachael.base.ui.util.SessionUtil;

@Layout
public final class MainLayout extends AppLayout {

	private static final long serialVersionUID = 5489847396390229579L;
	private User currentUser;

	public MainLayout(@Autowired UserService userService, @Autowired PaymentService paymentService) {
		this.currentUser = SessionUtil.getCurrentUser();
		buildLayout();
	}

	private void buildLayout() {
		setPrimarySection(Section.DRAWER);
		addToDrawer(createHeader(), new Scroller(createSideNav()), createUserMenu());
	}

	private Div createHeader() {
		var appLogo = VaadinIcon.CUBES.create();
		appLogo.addClassNames(TextColor.PRIMARY, IconSize.LARGE);

		var appName = new Span("Rachael App");
		appName.addClassNames(FontWeight.SEMIBOLD, FontSize.LARGE);

		var header = new Div(appLogo, appName);
		header.addClassNames(Display.FLEX, Padding.MEDIUM, Gap.MEDIUM, AlignItems.CENTER);
		return header;
	}

	private SideNav createSideNav() {
		var nav = new SideNav();
		nav.addClassNames(Margin.Horizontal.MEDIUM);

		// Aggiunge le voci generate automaticamente (dal MenuConfiguration)
		// MenuConfiguration.getMenuEntries().forEach(entry ->
		// nav.addItem(createSideNavItem(entry)));

		// Aggiunge voce: Home Page
		var homeIcon = VaadinIcon.HOME.create();
		var homeSideNavItem = new SideNavItem("Home", MainView.class, homeIcon);
		nav.addItem(homeSideNavItem);

		// Aggiunge voce: Wallet
		var albumIcon = VaadinIcon.DISC.create();
		var albumSideNavItem = new SideNavItem("Album", AlbumView.class, albumIcon);
		nav.addItem(albumSideNavItem);
		
		// Aggiunge voce: Wallet
		var walletIcon = VaadinIcon.WALLET.create();
		var walletSideNavItem = new SideNavItem("Wallet", WalletView.class, walletIcon);
		nav.addItem(walletSideNavItem);

		// Aggiunge voce: Gestione utenti
		var userIcon = VaadinIcon.USERS.create();
		var userSideNavItem = new SideNavItem("Utenti", UserView.class, userIcon);
		nav.addItem(userSideNavItem);

		return nav;
	}

	private SideNavItem createSideNavItem(MenuEntry menuEntry) {
		return menuEntry.icon() != null
				? new SideNavItem(menuEntry.title(), menuEntry.path(), new Icon(menuEntry.icon()))
				: new SideNavItem(menuEntry.title(), menuEntry.path());
	}

	private Component createUserMenu() {
		if (currentUser != null) {
			var avatar = new Avatar(currentUser.getName());
			avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL);
			avatar.addClassNames(Margin.Right.SMALL);
			avatar.setColorIndex(5);
			var userMenu = new MenuBar();
			userMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
			userMenu.addClassNames(Margin.MEDIUM);
			var userMenuItem = userMenu.addItem(avatar);
			userMenuItem.add(currentUser.getName());
			userMenuItem.getSubMenu().addItem("View Profile");
			userMenuItem.getSubMenu().addItem("Logout", event -> SessionUtil.logout());
			return userMenu;
		}
		return null;
	}

	

}

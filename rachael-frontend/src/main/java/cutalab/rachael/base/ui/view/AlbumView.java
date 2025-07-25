package cutalab.rachael.base.ui.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "album", layout = MainLayout.class)
@PageTitle("Album | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class AlbumView extends VerticalLayout {
	
	public AlbumView() {
		add(new H1("Album"));
	}
}

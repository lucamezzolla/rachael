package cutalab.rachael.base.ui.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "album", layout = MainLayout.class)
@PageTitle("Album | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class AlbumView extends VerticalLayout {
	
	private static final long serialVersionUID = -4533361550229762771L;

	public AlbumView() {
		setSizeFull();
        setAlignItems(Alignment.START);
        addClassName(LumoUtility.Padding.MEDIUM);

        add(new ViewToolbar(UIConstant.ALBUM_VIEW));
	}
}

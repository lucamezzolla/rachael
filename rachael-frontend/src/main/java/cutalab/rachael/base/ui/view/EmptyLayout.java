package cutalab.rachael.base.ui.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

@Tag("div") // necessario per evitare IllegalStateException
public class EmptyLayout extends VerticalLayout implements RouterLayout {
    private static final long serialVersionUID = 1L;

    public EmptyLayout() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
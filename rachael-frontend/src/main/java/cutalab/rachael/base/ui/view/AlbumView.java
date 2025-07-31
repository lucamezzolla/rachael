package cutalab.rachael.base.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import cutalab.rachael.backend.dto.album.*;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.base.ui.component.ViewToolbar;
import cutalab.rachael.base.ui.component.dialog.AlbumDialog;
import cutalab.rachael.base.ui.util.SessionUtil;
import cutalab.rachael.base.ui.view.costant.AlbumConstant;
import cutalab.rachael.base.ui.view.costant.AlbumConstant.AlbumDialogType;
import cutalab.rachael.base.ui.view.costant.UIConstant;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "album", layout = MainLayout.class)
@PageTitle("Album | Rachael App")
@RolesAllowed("USER")
@PreserveOnRefresh
public class AlbumView extends VerticalLayout {

	private static final long serialVersionUID = -4533361550229762771L;

	private static final int PAGE_SIZE = 5;

	private final DiskService diskService;
	private final Grid<DiskSummaryDTO> grid;
	private final List<DiskSummaryDTO> allDisks = new ArrayList<>();
	private int currentPage = 0;

	private Button firstPageButton;
	private Button prevButton;
	private Button nextButton;
	private Button lastPageButton;

	private TextField pageInput;
	private Span totalPagesLabel;

	public AlbumView(DiskService diskService) {
		this.diskService = diskService;
		setSizeFull();
		setAlignItems(Alignment.START);
		addClassName(LumoUtility.Padding.MEDIUM);

		add(new ViewToolbar(UIConstant.ALBUM_VIEW));
		add(createAddDiskButton());
		this.grid = createGrid();
		add(grid);
		add(createPaginationControls());

		fillGrid();
	}

	private Button createAddDiskButton() {
		Button button = new Button(AlbumConstant.DISK_FIELD_ADD_USER, e -> openDiskDialog(AlbumDialogType.CREATE, null));
		button.setPrefixComponent(VaadinIcon.PLUS.create());
		button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
		button.getStyle().set("margin-right", "auto");
		return button;
	}

	private Grid<DiskSummaryDTO> createGrid() {
		Grid<DiskSummaryDTO> grid = new Grid<>();
		grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);

		grid.addColumn(new ComponentRenderer<>(item -> {
			String cover = item.getCover() != null ? item.getCover() : "https://placehold.co/150x150";
			Image image = new Image(cover, "cover");
			image.setWidth("150px");
			image.setHeight("150px");
			image.getStyle().set("object-fit", "cover");
			return image;
		})).setWidth("200px").setFlexGrow(0);

		grid.addColumn(new ComponentRenderer<>(item -> {
			Div div = new Div();
			H3 title = new H3(item.getTitle());
			Span author = new Span(item.getAuthor());
			author.getStyle().set("font-size", "medium");
			div.add(title, author);
			return div;
		})).setAutoWidth(true).setFlexGrow(1);

		grid.addItemDoubleClickListener(e -> {
			DiskSummaryDTO selectedDisk = e.getItem();
			if (Objects.nonNull(selectedDisk)) {
				DiskResponseDTO response = diskService.getDiskById(selectedDisk.getId());
				openDiskDialog(AlbumDialogType.UPDATE, toDiskDTO(response));
			}
		});

		return grid;
	}

	private HorizontalLayout createPaginationControls() {
		firstPageButton = new Button(AlbumConstant.PAGINATION_FIRST, e -> {
			currentPage = 0;
			updateGridPage();
		});

		prevButton = new Button(AlbumConstant.PAGINATION_PREV, e -> {
			if (currentPage > 0) {
				currentPage--;
				updateGridPage();
			}
		});

		nextButton = new Button(AlbumConstant.PAGINATION_NEXT, e -> {
			if ((currentPage + 1) * PAGE_SIZE < allDisks.size()) {
				currentPage++;
				updateGridPage();
			}
		});

		lastPageButton = new Button(AlbumConstant.PAGINATION_LAST, e -> {
			currentPage = getTotalPages() - 1;
			updateGridPage();
		});

		firstPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		prevButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		nextButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		lastPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		pageInput = new TextField();
		pageInput.setWidth("4em");
		pageInput.setPlaceholder("1");
		pageInput.getElement().setAttribute("aria-label", "Pagina");
		pageInput.addKeyPressListener(Key.ENTER, e -> validateAndGoToPage());

		totalPagesLabel = new Span();
		totalPagesLabel.getStyle().set("font-weight", "500");

		HorizontalLayout layout = new HorizontalLayout(
			firstPageButton,
			prevButton,
			new Span(AlbumConstant.PAGINATION_PAGE_LABEL),
			pageInput,
			new Span(AlbumConstant.PAGINATION_PAGE_OF),
			totalPagesLabel,
			nextButton,
			lastPageButton
		);

		layout.setJustifyContentMode(JustifyContentMode.CENTER);
		layout.setAlignItems(Alignment.CENTER);
		layout.setWidthFull();
		layout.getStyle().set("margin-top", "1rem").set("gap", "0.5rem");

		return layout;
	}

	private void validateAndGoToPage() {
		String value = pageInput.getValue();
		try {
			int requestedPage = Integer.parseInt(value);
			int totalPages = getTotalPages();
			if (requestedPage >= 1 && requestedPage <= totalPages) {
				currentPage = requestedPage - 1;
				updateGridPage();
			} else {
				showPageError();
			}
		} catch (NumberFormatException ex) {
			// Ignora input non numerico
		}
	}

	private void showPageError() {
		Notification.show(AlbumConstant.PAGINATION_PAGE_ERROR, 3000, Notification.Position.TOP_CENTER)
			.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

	private int getTotalPages() {
		return (int) Math.ceil((double) allDisks.size() / PAGE_SIZE);
	}

	private void updateGridPage() {
		int fromIndex = currentPage * PAGE_SIZE;
		int toIndex = Math.min(fromIndex + PAGE_SIZE, allDisks.size());

		if (fromIndex >= toIndex) {
			grid.setItems();
		} else {
			grid.setItems(allDisks.subList(fromIndex, toIndex));
		}

		int totalPages = getTotalPages();

		firstPageButton.setEnabled(currentPage > 0);
		prevButton.setEnabled(currentPage > 0);
		nextButton.setEnabled(currentPage < totalPages - 1);
		lastPageButton.setEnabled(currentPage < totalPages - 1);

		pageInput.setValue(String.valueOf(currentPage + 1));
		totalPagesLabel.setText(String.valueOf(totalPages));

		getElement().executeJs("window.scrollTo({ top: 0, behavior: 'smooth' })");
	}

	private void fillGrid() {
		DiskListRequestDTO request = new DiskListRequestDTO();
		request.setUserId(SessionUtil.getCurrentUser().getId());
		DiskListResponseDTO response = diskService.getAllDisks(request);
		allDisks.clear();
		allDisks.addAll(response.getDisks());
		currentPage = 0;
		updateGridPage();
	}

	private void openDiskDialog(AlbumDialogType type, DiskDTO disk) {
		AlbumDialog dialog = new AlbumDialog(type, disk, diskService, this::fillGrid);
		dialog.open();
	}

	private DiskDTO toDiskDTO(DiskResponseDTO response) {
		DiskDTO dto = new DiskDTO();
		dto.setId(response.getId());
		dto.setTitle(response.getTitle());
		dto.setAuthor(response.getAuthor());
		dto.setLabel(response.getLabel());
		dto.setYear(response.getYear());
		dto.setReprint(response.getReprint());
		dto.setNote(response.getNote());
		dto.setCover(response.getCover());
		dto.setOpenable(response.isOpenable());
		dto.setPresumedValue(response.getPresumedValue());
		dto.setCoverStatus(response.getCoverStatus());
		dto.setDiskStatus(response.getDiskStatus());
		dto.setGenres(response.getGenres());
		dto.setStyles(response.getStyles());
		dto.setUserId(response.getUserId());
		return dto;
	}
	
}
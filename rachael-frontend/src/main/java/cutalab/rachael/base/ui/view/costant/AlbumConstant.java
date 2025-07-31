package cutalab.rachael.base.ui.view.costant;

public class AlbumConstant {

    public enum AlbumDialogType {
        CREATE, UPDATE
    }
    
    public enum AlbumGenreStyleType {
    	GENRE, STYLE
    }

    // Titoli dialog
    public static final String DISK_TITLE_CREATE = "Aggiungi disco";
    public static final String DISK_TITLE_UPDATE = "Modifica disco";

    // Etichette campi
    public static final String DISK_FIELD_TITLE = "Titolo";
    public static final String DISK_FIELD_AUTHOR = "Autore";
    public static final String DISK_FIELD_LABEL = "Etichetta";
    public static final String DISK_FIELD_YEAR = "Anno";
    public static final String DISK_FIELD_REPRINT = "Ristampa";
    public static final String DISK_FIELD_OPENABLE = "Apribile";
    public static final String DISK_FIELD_VALUE = "Valore presunto";
    public static final String DISK_FIELD_DISK_STATUS = "Stato disco";
    public static final String DISK_FIELD_COVER_STATUS = "Stato copertina";
    public static final String DISK_FIELD_NOTE = "Note";
    public static final String DISK_FIELD_ADD_USER = "Aggiungi disco";
    public static final String ADD_GENRE = "Associa generi";
	public static final String ADD_STYLE = "Associa stili";
	
	// Paginazione
	public static final String PAGINATION_FIRST = "⏮";
	public static final String PAGINATION_PREV = "←";
	public static final String PAGINATION_NEXT = "→";
	public static final String PAGINATION_LAST = "⏭";
	public static final String PAGINATION_PAGE_LABEL = "Pagina";
	public static final String PAGINATION_PAGE_OF = "di";
	public static final String PAGINATION_PAGE_ERROR = "Pagina fuori intervallo";

    // Messaggi di validazione
    public static final String DISK_VALIDATION_FIELD = "Campo obbligatorio";
    
}
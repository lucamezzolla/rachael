package cutalab.rachael.base.ui.view.costant;

public class UserConstant {
	
	public enum UserDialogType {
	    CREATE, UPDATE
	}

	// Titoli
	public static final String USER_TITLE_CREATE = "Crea utente";
	public static final String USER_TITLE_UPDATE = "Modifica utente";
	public static final String USER_TITLE_CHANGE_PASSWORD = "Cambia Password";

	// Label campi
	public static final String USER_FIELD_NAME = "Nome completo";
	public static final String USER_FIELD_EMAIL = "Email";
	public static final String USER_FIELD_PASSWORD = "Password";
	public static final String USER_FIELD_CHANGE_PASSWORD = "Cambia password";
	public static final String USER_FIELD_NEW_PASSWORD = "Nuova Password";
	public static final String USER_FIELD_CONFIRM_PASSWORD = "Conferma Password";
	public static final String USER_FIELD_ADD_USER 		= "Aggiungi utente";

	// Messaggi di validazione
	public static final String VALIDATION_NAME_REQUIRED = "Il nome è obbligatorio";
	public static final String VALIDATION_EMAIL_REQUIRED = "L'email è obbligatoria";
	public static final String VALIDATION_EMAIL_INVALID = "Email non valida";
	public static final String VALIDATION_PASSWORD_REQUIRED = "La password è obbligatoria";
	public static final String VALIDATION_CONFIRM_REQUIRED = "La conferma è obbligatoria";
	public static final String VALIDATION_PASSWORD_TOO_SHORT = "Minimo 6 caratteri";
	public static final String VALIDATION_PASSWORD_MISMATCH = "Le password non coincidono";

}

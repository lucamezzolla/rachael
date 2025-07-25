package cutalab.rachael.base.ui.view.costant;

public class PaymentConstant {
	
	public enum PaymentDialogType {
	    CREATE, UPDATE
	}

	public enum PaymentType {
	    LUCE("Luce"),
	    GAS("Gas"),
	    ACQUA("Acqua"),
	    CONDOMINIO("Condominio"),
	    ASSICURAZIONE_MACCHINA("Assicurazione macchina"),
	    MANUTENZIONE_MACCHINA("Manutenzione macchina"),
	    ALTRO("Altro");

	    private final String label;

	    PaymentType(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	}
	
	// Titoli
	public static final String PAYMENT_TITLE_CREATE 			= "Crea pagamento";
	public static final String PAYMENT_TITLE_UPDATE 			= "Modifica pagamento";
	
	// Label campi
	public static final String PAYMENT_FIELD_AMOUNT 			= "Prezzo";
	public static final String PAYMENT_FIELD_DATE 				= "Data di pagamento";
	public static final String PAYMENT_FIELD_DUE_DATE 			= "Data di scadenza";
	public static final String PAYMENT_FIELD_PAID 				= "Pagato";
	public static final String PAYMENT_FIELD_EXTRA_DETAILS  	= "Dettagli";
	public static final String PAYMENT_FIELD_ADD_PAYMENT 		= "Aggiungi pagamento";
	public static final String PAYMENT_FIELD_PAYMENT_TYPE		= "Tipo di pagamento";
	public static final String PAYMENT_FIELD_PAYMENT_YEAR		= "Anno di riferimento";
	
	//Label campi extra details CONDOMINIO
	public static final String PAYMENT_FIELD_CONDOMINIUM_NAME				= "Nome condominio";
	public static final String PAYMENT_FIELD_CONDOMINIUM_FISCAL_YEAR		= "Esercizio (anno)";
	public static final String PAYMENT_FIELD_CONDOMINIUM_RECEIPT_NO			= "Ricevuta nr.";
	public static final String PAYMENT_FIELD_CONDOMINIUM_DEBTOR				= "Debitore";
	public static final String PAYMENT_FIELD_CONDOMINIUM_DESCRIPTION		= "Causale";
	public static final String PAYMENT_FIELD_CONDOMINIUM_IBAN				= "Dettagli IBAN";
	
	//Label campi extra details LUCE, GAS, ACQUA
	public static final String PAYMENT_FIELD_LGA_CONSUMPTION				= "Consumo";
	public static final String PAYMENT_FIELD_LGA_SUPPLIER					= "Fornitore";

}

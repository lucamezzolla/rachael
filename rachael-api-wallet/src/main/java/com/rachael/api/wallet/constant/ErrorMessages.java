package com.rachael.api.wallet.constant;

public class ErrorMessages {
	
	public static final String BAD_REQUEST = "Richiesta non valida.";
	public static final String NOT_FOUND = "Non trovato.";
	public static final String INTERNAL_SERVER_ERROR = "Errore interno del server.";
    public static final String INVALID_CREDENTIALS = "Credenziali non valide.";
    
    //PAGAMENTO
    public static final String PAYMENT_NOT_FOUND = "Pagamento non trovato con id: ";
    public static final String TOKEN_NOT_FOUND = "Token JWT mancante nell'header Authorization";
    public static final String INVALID_TOKEN = "Token JWT non valido";
    public static final String ON_CREATE_ERROR = "Errore durante salvataggio pagamento";
    public static final String ON_UPDATE_ERROR = "Errore durante aggiornamento pagamento";
    
    private ErrorMessages() {}
    
}
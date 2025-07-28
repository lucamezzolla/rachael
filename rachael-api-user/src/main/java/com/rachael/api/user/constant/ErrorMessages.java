package com.rachael.api.user.constant;

public class ErrorMessages {
	
	public static final String INTERNAL_SERVER_ERROR = "Errore interno del server.";
    public static final String INVALID_CREDENTIALS = "Credenziali non valide.";
    
    //UTENTE
    public static final String USER_NOT_FOUND = "Utente non trovato con l'email fornita.";
    public static final String BAD_REQUEST = "Richiesta non valida.";
    public static final String TOKEN_NOT_FOUND = "Token non trovato.";
    public static final String INVALID_TOKEN = "Token invalido.";
    public static final String USER_NOT_FOUND_WITH_ID(Long id) {
    	return "Utente non trovato con id ".concat(String.valueOf(id));
    }
    
    private ErrorMessages() {}
    
}
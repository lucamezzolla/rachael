package com.rachael.api.album.constant;

public class ErrorMessages {
	
	public static final String BAD_REQUEST = "Richiesta non valida.";
	public static final String NOT_FOUND = "Non trovato.";
	public static final String INTERNAL_SERVER_ERROR = "Errore interno del server.";
    public static final String INVALID_CREDENTIALS = "Credenziali non valide.";
    
    //ALBUM
    public static final String ALBUM_NOT_FOUND = "Album non trovato con id: ";
    public static final String TOKEN_NOT_FOUND = "Token JWT mancante nell'header Authorization";
    public static final String INVALID_TOKEN = "Token JWT non valido";
    public static final String ON_CREATE_ERROR = "Errore durante salvataggio album";
    public static final String ON_UPDATE_ERROR = "Errore durante aggiornamento album";
    
    private ErrorMessages() {}
    
}
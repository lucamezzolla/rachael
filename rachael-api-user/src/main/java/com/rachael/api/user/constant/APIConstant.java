package com.rachael.api.user.constant;

public class APIConstant {

    // Base Path
    public static final String PATH = "/api/user";

    // Security
    public static final String SECURITY_REQUIREMENT_NAME = "bearerAuth";

    // Swagger / OpenAPI Info
    public static final String OPENAPI_TITLE = "Rachael User API";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "Documentazione API per la gestione degli utenti";
    public static final String OPENAPI_SECURITY_SCHEME = "bearer";
    public static final String OPENAPI_SECURITY_SCHEME_NAME = "Authorization";
    public static final String OPENAPI_BEARER_FORMAT = "JWT";

    // Controller Tag
    public static final String USERCONTOLLER_TAG_NAME = "Utente";
    public static final String USERCONTROLLER_TAG_DESCRIPTION = "Operazioni relative agli utenti";

    // LOGIN
    public static final String USERCONTROLLER_LOGIN_MAPPING = "/login";
    public static final String USERCONTROLLER_LOGIN_OPERATION_SUMMARY = "Login utente";
    public static final String USERCONTROLLER_LOGIN_OPERATION_DESCRIPTION = "Effettua il login con email e password";

    // REGISTER
    public static final String USERCONTROLLER_REGISTER_MAPPING = "/register";
    public static final String USERCONTROLLER_REGISTER_OPERATION_SUMMARY = "Registrazione utente";
    public static final String USERCONTROLLER_REGISTER_OPERATION_DESCRIPTION = "Registra un nuovo utente nel sistema";

    // UPDATE
    public static final String USERCONTROLLER_UPDATE_MAPPING = "/{id}";
    public static final String USERCONTROLLER_UPDATE_OPERATION_SUMMARY = "Modifica utente";
    public static final String USERCONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Aggiorna i dati di un utente esistente identificato da ID";
    
    // GET USER
    public static final String USERCONTROLLER_GET_BY_MAPPING = "/{id}";
    public static final String USERCONTROLLER_GET_BY_ID_OPERATION_SUMMARY = "Recupera utente";
    public static final String USERCONTROLLER_GET_BY_ID_OPERATION_DESCRIPTION = "Restituisce i dettagli di un utente tramite ID";

    // GET ALL USERS
    public static final String USERCONTROLLER_GET_ALL_MAPPING = "";
    public static final String USERCONTROLLER_GET_ALL_OPERATION_SUMMARY = "Elenco utenti";
    public static final String USERCONTROLLER_GET_ALL_OPERATION_DESCRIPTION = "Restituisce la lista di tutti gli utenti presenti nel sistema";

    // DELETE USER
    public static final String USERCONTROLLER_DELETE_MAPPING = "/{id}";
    public static final String USERCONTROLLER_DELETE_OPERATION_SUMMARY = "Elimina utente";
    public static final String USERCONTROLLER_DELETE_OPERATION_DESCRIPTION = "Elimina un utente tramite il suo identificativo";
    
}
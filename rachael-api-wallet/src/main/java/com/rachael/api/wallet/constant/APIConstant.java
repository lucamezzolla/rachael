package com.rachael.api.wallet.constant;

public class APIConstant {

    // Base Path
    public static final String PATH = "/api/user";

    // Security
    public static final String SECURITY_REQUIREMENT_NAME = "bearerAuth";

    // Swagger / OpenAPI Info
    public static final String OPENAPI_TITLE = "Rachael Wallet API";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "Documentazione API per la gestione del portafoglio";
    public static final String OPENAPI_SECURITY_SCHEME = "bearer";
    public static final String OPENAPI_SECURITY_SCHEME_NAME = "Authorization";
    public static final String OPENAPI_BEARER_FORMAT = "JWT";
    
    public static final String PAYMENT_PATH = "/api/payment";

    public static final String PAYMENT_TAG_NAME = "Pagamento";
    public static final String PAYMENT_TAG_DESCRIPTION = "Gestione dei pagamenti di casa";

    public static final String PAYMENT_CREATE_MAPPING = "";
    public static final String PAYMENT_GET_ALL_MAPPING = "/find";
    public static final String PAYMENT_GET_BY_ID_MAPPING = "/{id}";
    public static final String PAYMENT_UPDATE_MAPPING = "/{id}";
    public static final String PAYMENT_DELETE_MAPPING = "/{id}";

    public static final String PAYMENT_CREATE_OPERATION_SUMMARY = "Crea un pagamento";
    public static final String PAYMENT_CREATE_OPERATION_DESCRIPTION = "Inserisce un nuovo pagamento con i dettagli forniti";

    public static final String PAYMENT_GET_ALL_OPERATION_SUMMARY = "Elenco pagamenti";
    public static final String PAYMENT_GET_ALL_OPERATION_DESCRIPTION = "Recupera tutti i pagamenti registrati";

    public static final String PAYMENT_GET_BY_ID_OPERATION_SUMMARY = "Dettaglio pagamento";
    public static final String PAYMENT_GET_BY_ID_OPERATION_DESCRIPTION = "Restituisce un pagamento specifico tramite ID";

    public static final String PAYMENT_UPDATE_OPERATION_SUMMARY = "Aggiorna pagamento";
    public static final String PAYMENT_UPDATE_OPERATION_DESCRIPTION = "Aggiorna i dettagli di un pagamento esistente tramite ID";

    public static final String PAYMENT_DELETE_OPERATION_SUMMARY = "Elimina pagamento";
    public static final String PAYMENT_DELETE_OPERATION_DESCRIPTION = "Rimuove un pagamento esistente tramite ID";
    
}
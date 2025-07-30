package com.rachael.api.album.constant;

public class APIConstant {

    // Base Path
    public static final String PATH = "/api/disk";

    // Security
    public static final String SECURITY_REQUIREMENT_NAME = "bearerAuth";

    // Swagger / OpenAPI Info
    public static final String OPENAPI_TITLE = "Rachael Album API";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "Documentazione API per la gestione degli album";
    public static final String OPENAPI_SECURITY_SCHEME = "bearer";
    public static final String OPENAPI_SECURITY_SCHEME_NAME = "Authorization";
    public static final String OPENAPI_BEARER_FORMAT = "JWT";

    // Controller Tag
    public static final String ALBUMCONTOLLER_TAG_NAME = "Album";
    public static final String ALBUMCONTROLLER_TAG_DESCRIPTION = "Operazioni relative agli album";
    
    
    // Recupera tutti gli album
    public static final String ALBUM_GET_ALL_OPERATION_SUMMARY = "Album";
    public static final String ALBUM_GET_ALL_OPERATION_DESCRIPTION = "Album";
    public static final String ALBUM_GET_ALL_MAPPING = "/find";

    
    // Mapping endpoint singoli
    public static final String DISK_GET_BY_ID_MAPPING = "/{id}";
    public static final String DISK_CREATE_MAPPING = "";
    public static final String DISK_UPDATE_MAPPING = "/{id}";
    public static final String DISK_STATUS = "/status";

    // Descrizioni OpenAPI (opzionali)
    public static final String DISK_GET_BY_ID_SUMMARY = "Recupera un disco per ID";
    public static final String DISK_GET_BY_ID_DESCRIPTION = "Restituisce un singolo disco in base all'ID";

    public static final String DISK_CREATE_SUMMARY = "Crea un nuovo disco";
    public static final String DISK_CREATE_DESCRIPTION = "Salva un nuovo disco con tutti i dettagli forniti";

    public static final String DISK_UPDATE_SUMMARY = "Aggiorna un disco esistente";
    public static final String DISK_UPDATE_DESCRIPTION = "Aggiorna i dati di un disco già presente";
    
    public static final String DISK_ALL_GENRE_MAPPING = "/genres";
    public static final String DISK_ALL_STYLE_MAPPING = "/styles";
    
}
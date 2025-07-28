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
    
//    public static final String ALBUM_CREATE_MAPPING = "";
    public static final String ALBUM_GET_ALL_MAPPING = "/find";
//    public static final String ALBUM_GET_BY_ID_MAPPING = "/{id}";
//    public static final String ALBUM_UPDATE_MAPPING = "/{id}";
//    public static final String ALBUM_DELETE_MAPPING = "/{id}";
    
    // Recupera tutti gli album
    public static final String ALBUM_GET_ALL_OPERATION_SUMMARY = "Album";
    public static final String ALBUM_GET_ALL_OPERATION_DESCRIPTION = "Album";


    
}
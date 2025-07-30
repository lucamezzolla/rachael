package com.rachael.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rachael.api.user.constant.APIConstant;
import com.rachael.api.user.dto.GenericResponse;
import com.rachael.api.user.dto.UserListResponse;
import com.rachael.api.user.dto.UserLoginRequest;
import com.rachael.api.user.dto.UserPasswordRequest;
import com.rachael.api.user.dto.UserRequest;
import com.rachael.api.user.dto.UserResponse;
import com.rachael.api.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(APIConstant.PATH)
@SecurityRequirement(name = APIConstant.SECURITY_REQUIREMENT_NAME)
@Tag(name = APIConstant.USERCONTOLLER_TAG_NAME, description = APIConstant.USERCONTROLLER_TAG_DESCRIPTION)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Effettua il login dell’utente utilizzando email e password.
     *
     * @param request oggetto {@link UserLoginRequest} con le credenziali dell’utente
     * @return {@link UserResponse} contenente i dati utente e token in caso di successo
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_LOGIN_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_LOGIN_OPERATION_DESCRIPTION
    )
    @PostMapping(APIConstant.USERCONTROLLER_LOGIN_MAPPING)
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request) {
        UserResponse userResponse = userService.login(request);
        return ResponseEntity.status(userResponse.getHttpStatus()).body(userResponse);
    }

    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param userRequest {@link UserRequest} contenente i dati dell’utente da creare
     * @return {@link UserResponse} con i dettagli del nuovo utente creato
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_REGISTER_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_REGISTER_OPERATION_DESCRIPTION
    )
    @PostMapping(APIConstant.USERCONTROLLER_REGISTER_MAPPING)
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    /**
     * Aggiorna i dati di un utente esistente identificato da ID.
     *
     * @param id identificativo dell’utente da aggiornare
     * @param request {@link UserRequest} con i nuovi dati da applicare
     * @return {@link UserResponse} con i dati aggiornati dell’utente
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_UPDATE_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_UPDATE_OPERATION_DESCRIPTION
    )
    @PutMapping(APIConstant.USERCONTROLLER_UPDATE_MAPPING)
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
    
    /**
     * Restituisce il dettaglio dell'utente richiesto e presente nel sistema.
     *
     * @return {@link UserResponse} contenente il dettaglio dell'utente richiesto
     */
    @Operation(summary = APIConstant.USERCONTROLLER_GET_BY_ID_OPERATION_SUMMARY, description = APIConstant.USERCONTROLLER_GET_BY_ID_OPERATION_DESCRIPTION)
    @GetMapping(APIConstant.USERCONTROLLER_GET_BY_MAPPING)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    /**
     * Restituisce la lista di tutti gli utenti presenti nel sistema.
     *
     * @return {@link UserListResponse} contenente l’elenco degli utenti e metadati della risposta
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_GET_ALL_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_GET_ALL_OPERATION_DESCRIPTION
    )
    @GetMapping(APIConstant.USERCONTROLLER_GET_ALL_MAPPING)
    public ResponseEntity<UserListResponse> getAllUsers() {
        UserListResponse response = userService.getAllUsers();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    /**
     * Elimina un utente dal sistema tramite il suo identificativo.
     *
     * @param id identificativo dell’utente da eliminare
     * @return {@link GenericResponse} con messaggio di conferma o errore
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_DELETE_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_DELETE_OPERATION_DESCRIPTION
    )
    @DeleteMapping(APIConstant.USERCONTROLLER_DELETE_MAPPING)
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable Long id) {
        GenericResponse response = userService.deleteUser(id);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
    
    /**
     * Cambia la password di un utente tramite ID.
     *
     * @param id identificativo dell'utente da aggiornare
     * @param password da modificare
     * @return {@link GenericResponse} con informazioni sull'esito dell'operazione (successo o errore)
     */
    @Operation(
        summary = APIConstant.USERCONTROLLER_CHANGE_PASSWORD_OPERATION_SUMMARY,
        description = APIConstant.USERCONTROLLER_CHANGE_PASSWORD_OPERATION_DESCRIPTION
    )
    @PutMapping(APIConstant.USERCONTROLLER_CHANGE_PASSWORD_MAPPING)
    public ResponseEntity<GenericResponse> changePassword(@PathVariable Long id, @RequestBody UserPasswordRequest request) {
    	GenericResponse response = userService.changePassword(id, request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
    
}
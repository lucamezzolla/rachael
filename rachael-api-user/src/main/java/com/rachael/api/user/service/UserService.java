package com.rachael.api.user.service;

import com.rachael.api.user.dto.GenericResponse;
import com.rachael.api.user.dto.UserListResponse;
import com.rachael.api.user.dto.UserLoginRequest;
import com.rachael.api.user.dto.UserRequest;
import com.rachael.api.user.dto.UserResponse;

/**
 * Interfaccia per la gestione delle operazioni relative agli utenti.
 * Fornisce metodi per autenticazione, registrazione, aggiornamento, eliminazione e recupero utenti.
 */
public interface UserService {

    /**
     * Autentica un utente utilizzando le credenziali fornite.
     *
     * @param request oggetto contenente email e password dell'utente
     * @return {@link UserResponse} contenente i dati dell'utente autenticato, token JWT, 
     *         e informazioni sullo stato della risposta (es. errore se credenziali errate)
     */
    UserResponse login(UserLoginRequest request);

    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param request oggetto contenente nome, email e password dell'utente
     * @return {@link UserResponse} con i dati dell'utente creato e dettagli sull'esito dell'operazione
     */
    UserResponse createUser(UserRequest request);

    /**
     * Aggiorna le informazioni di un utente esistente.
     *
     * @param id identificativo dell'utente da aggiornare
     * @param request oggetto contenente i nuovi dati dell'utente
     * @return {@link UserResponse} con i dati aggiornati e dettagli sull'esito
     */
    UserResponse updateUser(Long id, UserRequest request);
    
    /**
     * Recupera i dettagli di un utente tramite il suo ID.
     *
     * @param id identificativo dell'utente da recuperare
     * @return {@link UserResponse} contenente i dati dell'utente e lo stato della risposta
     */
    UserResponse getUserById(Long id);

    /**
     * Restituisce una lista di tutti gli utenti registrati nel sistema.
     *
     * @return {@link UserListResponse} contenente la lista degli utenti e informazioni sullo stato della risposta
     */
    UserListResponse getAllUsers();

    /**
     * Elimina un utente dal sistema tramite il suo ID.
     *
     * @param id identificativo dell'utente da eliminare
     * @return {@link GenericResponse} con informazioni sull'esito dell'operazione (successo o errore)
     */
    GenericResponse deleteUser(Long id);
    
}
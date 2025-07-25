package com.rachael.api.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachael.api.wallet.constant.APIConstant;
import com.rachael.api.wallet.dto.GenericResponse;
import com.rachael.api.wallet.dto.PaymentListRequest;
import com.rachael.api.wallet.dto.PaymentListResponse;
import com.rachael.api.wallet.dto.PaymentRequest;
import com.rachael.api.wallet.dto.PaymentResponse;
import com.rachael.api.wallet.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(APIConstant.PAYMENT_PATH)
@SecurityRequirement(name = APIConstant.SECURITY_REQUIREMENT_NAME)
@Tag(name = APIConstant.PAYMENT_TAG_NAME, description = APIConstant.PAYMENT_TAG_DESCRIPTION)
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	/**
	 * Crea un nuovo pagamento.
	 *
	 * @param request {@link PaymentRequest} contenente i dettagli del pagamento
	 * @return {@link PaymentResponse} con i dati del pagamento creato
	 */
	@Operation(summary = APIConstant.PAYMENT_CREATE_OPERATION_SUMMARY, description = APIConstant.PAYMENT_CREATE_OPERATION_DESCRIPTION)
	@PostMapping(APIConstant.PAYMENT_CREATE_MAPPING)
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
		PaymentResponse response = paymentService.createPayment(request);
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

	/**
	 * Restituisce tutti i pagamenti.
	 *
	 * @return lista di {@link PaymentListResponse}
	 */
	@Operation(summary = APIConstant.PAYMENT_GET_ALL_OPERATION_SUMMARY, description = APIConstant.PAYMENT_GET_ALL_OPERATION_DESCRIPTION)
	@PostMapping(APIConstant.PAYMENT_GET_ALL_MAPPING)
	public ResponseEntity<PaymentListResponse> getAllPayments(@RequestBody PaymentListRequest request) {
		return ResponseEntity.ok(paymentService.getAllPayments(request));
	}

	/**
	 * Restituisce i dettagli di un pagamento specifico.
	 *
	 * @param id identificativo del pagamento
	 * @return {@link PaymentResponse}
	 */
	@Operation(summary = APIConstant.PAYMENT_GET_BY_ID_OPERATION_SUMMARY, description = APIConstant.PAYMENT_GET_BY_ID_OPERATION_DESCRIPTION)
	@GetMapping(APIConstant.PAYMENT_GET_BY_ID_MAPPING)
	public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.getPaymentById(id));
	}

	/**
	 * Aggiorna un pagamento esistente.
	 *
	 * @param id      identificativo del pagamento
	 * @param request {@link PaymentRequest} con i nuovi dati
	 * @return {@link PaymentResponse}
	 */
	@Operation(summary = APIConstant.PAYMENT_UPDATE_OPERATION_SUMMARY, description = APIConstant.PAYMENT_UPDATE_OPERATION_DESCRIPTION)
	@PutMapping(APIConstant.PAYMENT_UPDATE_MAPPING)
	public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @RequestBody PaymentRequest request) {

		PaymentResponse response = paymentService.updatePayment(id, request);
		return ResponseEntity.ok(response);
	}

	/**
	 * Elimina un pagamento.
	 *
	 * @param id identificativo del pagamento
	 * @return {@link GenericResponse}
	 */
	@Operation(summary = APIConstant.PAYMENT_DELETE_OPERATION_SUMMARY, description = APIConstant.PAYMENT_DELETE_OPERATION_DESCRIPTION)
	@DeleteMapping(APIConstant.PAYMENT_DELETE_MAPPING)
	public ResponseEntity<GenericResponse> deletePayment(@PathVariable Long id) {
		GenericResponse response = paymentService.deletePayment(id);
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

}

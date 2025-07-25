package cutalab.rachael.backend.dto.service;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.wallet.PaymentListRequest;
import cutalab.rachael.backend.dto.wallet.PaymentListResponse;
import cutalab.rachael.backend.dto.wallet.PaymentRequest;
import cutalab.rachael.backend.dto.wallet.PaymentResponse;
import cutalab.rachael.backend.model.Payment;

public interface PaymentService {

	PaymentResponse createPayment(PaymentRequest request);
	PaymentListResponse getAllPayments(PaymentListRequest request);
    Payment getPaymentById(Long id);
    PaymentResponse updatePayment(Long id, PaymentRequest request);
    GenericResponse deletePayment(Long id);
    
}
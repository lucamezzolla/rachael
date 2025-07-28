package com.rachael.api.wallet.service;

import com.rachael.api.wallet.dto.GenericResponse;
import com.rachael.api.wallet.dto.PaymentListRequest;
import com.rachael.api.wallet.dto.PaymentListResponse;
import com.rachael.api.wallet.dto.PaymentRequest;
import com.rachael.api.wallet.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);
    PaymentListResponse getAllPayments(PaymentListRequest request);
    PaymentResponse getPaymentById(Long id);
    PaymentResponse updatePayment(Long id, PaymentRequest request);
    GenericResponse deletePayment(Long id);
    
}
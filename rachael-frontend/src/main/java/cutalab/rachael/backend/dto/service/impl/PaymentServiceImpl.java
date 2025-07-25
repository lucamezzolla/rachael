package cutalab.rachael.backend.dto.service.impl;

import org.springframework.stereotype.Service;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.service.PaymentService;
import cutalab.rachael.backend.dto.wallet.PaymentListRequest;
import cutalab.rachael.backend.dto.wallet.PaymentListResponse;
import cutalab.rachael.backend.dto.wallet.PaymentRequest;
import cutalab.rachael.backend.dto.wallet.PaymentResponse;
import cutalab.rachael.backend.model.Payment;
import cutalab.rachael.backend.proxy.PaymentProxy;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentProxy paymentProxy;
	
	public PaymentServiceImpl(PaymentProxy paymentProxy) {
		this.paymentProxy = paymentProxy;
	}

	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		return paymentProxy.createPayment(request).getBody();
	}

	@Override
	public PaymentListResponse getAllPayments(PaymentListRequest request) {
		return paymentProxy.getAllPayments(request).getBody();
	}

	@Override
	public Payment getPaymentById(Long id) {
		var response = paymentProxy.getPaymentById(id).getBody();
		var payment = new Payment();
		payment.setId(response.getId());
		payment.setAmount(response.getAmount());
		payment.setDate(response.getDate());
		payment.setDueDate(response.getDueDate());
		payment.setExtraDetails(response.getExtraDetails());
		payment.setPaid(response.isPaid());
		payment.setType(response.getType());
		return payment;
	}

	@Override
	public PaymentResponse updatePayment(Long id, PaymentRequest request) {
		return paymentProxy.updatePayment(id, request).getBody();
	}

	@Override
	public GenericResponse deletePayment(Long id) {
		return paymentProxy.deletePayment(id).getBody();
	}

}
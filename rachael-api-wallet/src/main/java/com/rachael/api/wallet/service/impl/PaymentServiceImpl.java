package com.rachael.api.wallet.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rachael.api.wallet.constant.ErrorMessages;
import com.rachael.api.wallet.constant.SuccessMessages;
import com.rachael.api.wallet.dto.GenericResponse;
import com.rachael.api.wallet.dto.PaymentListRequest;
import com.rachael.api.wallet.dto.PaymentListResponse;
import com.rachael.api.wallet.dto.PaymentRequest;
import com.rachael.api.wallet.dto.PaymentResponse;
import com.rachael.api.wallet.exception.ResourceNotFoundException;
import com.rachael.api.wallet.model.Payment;
import com.rachael.api.wallet.repository.PaymentRepository;
import com.rachael.api.wallet.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		try {
			Payment payment = Payment.builder().amount(request.getAmount()).date(request.getDate())
					.dueDate(request.getDueDate()).paid(request.isPaid()).type(request.getType())
					.extraDetails(request.getExtraDetails()).build();

			Payment saved = paymentRepository.save(payment);
			return PaymentResponse.builder().id(saved.getId()).amount(saved.getAmount()).date(saved.getDate())
					.dueDate(saved.getDueDate()).paid(saved.isPaid()).type(saved.getType())
					.extraDetails(saved.getExtraDetails()).timestamp(LocalDateTime.now())
					.message(SuccessMessages.PAYMENT_CREATED).httpStatus(HttpStatus.CREATED).build();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(ErrorMessages.ON_CREATE_ERROR, e);
		}
	}

	@Override
	public PaymentListResponse getAllPayments(PaymentListRequest request) {
		int year = request.getYear();
		var type = request.getType();
		List<PaymentResponse> response = paymentRepository.findByYearAndType(year, type)
            .stream()
            .map(payment -> PaymentResponse.builder()
                    .id(payment.getId())
                    .amount(payment.getAmount())
                    .date(payment.getDate())
            		.dueDate(payment.getDueDate())
                    .paid(payment.isPaid())
                    .type(payment.getType())
                    .extraDetails(payment.getExtraDetails())
                    .build())
            .collect(Collectors.toList());
        List<Payment> payments = new ArrayList<>();
        for(PaymentResponse res : response) {
        	var payment = new Payment();
        	payment.setId(res.getId());
        	payment.setAmount(res.getAmount());
        	payment.setDate(res.getDate());
        	payment.setDueDate(res.getDueDate());
        	payment.setPaid(res.isPaid());
        	payment.setType(res.getType());
        	payment.setExtraDetails(res.getExtraDetails());
        	payments.add(payment);
        }
        return PaymentListResponse.builder()
            .payments(payments)
            .timestamp(LocalDateTime.now())
            .httpStatus(HttpStatus.OK)
            .message(SuccessMessages.PAYMENT_LIST_FOUND)
            .build();
	}

	@Override
	public PaymentResponse getPaymentById(Long id) {
		Payment payment = findOrThrow(id);
		return toResponse(payment);
	}

	@Override
	public PaymentResponse updatePayment(Long id, PaymentRequest request) {
		try {
			Payment existing = findOrThrow(id);

			existing.setAmount(request.getAmount());
			existing.setDate(request.getDate());
			existing.setDueDate(request.getDueDate());
			existing.setPaid(request.isPaid());
			existing.setType(request.getType());
			existing.setExtraDetails(request.getExtraDetails());

			Payment updated = paymentRepository.save(existing);

			return PaymentResponse.builder().id(updated.getId()).amount(updated.getAmount()).date(updated.getDate())
					.dueDate(updated.getDueDate()).paid(updated.isPaid()).type(updated.getType())
					.extraDetails(updated.getExtraDetails()).timestamp(LocalDateTime.now())
					.message(SuccessMessages.PAYMENT_UPDATED).httpStatus(HttpStatus.OK).build();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(ErrorMessages.ON_UPDATE_ERROR, e);
		}
	}

	@Override
	public GenericResponse deletePayment(Long id) {
	    Payment payment = paymentRepository.findById(id).orElse(null);
	    if (payment == null) {
	        return GenericResponse.builder()
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(ErrorMessages.PAYMENT_NOT_FOUND + id)
                .build();
	    }
	    paymentRepository.delete(payment);
	    return GenericResponse.builder()
            .timestamp(LocalDateTime.now())
            .httpStatus(HttpStatus.OK)
            .message(SuccessMessages.PAYMENT_DELETED)
            .build();
	}


	private Payment findOrThrow(Long id) {
		return paymentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ErrorMessages.PAYMENT_NOT_FOUND.concat(String.valueOf(id))));
	}

	private PaymentResponse toResponse(Payment payment) {
		return PaymentResponse.builder().id(payment.getId()).amount(payment.getAmount()).date(payment.getDate())
				.dueDate(payment.getDueDate()).paid(payment.isPaid()).type(payment.getType())
				.extraDetails(payment.getExtraDetails()).build();
	}

}
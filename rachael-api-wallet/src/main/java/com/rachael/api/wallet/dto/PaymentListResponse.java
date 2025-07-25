package com.rachael.api.wallet.dto;

import java.util.List;

import com.rachael.api.wallet.model.Payment;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PaymentListResponse extends GenericResponse {
	
    private List<Payment> payments;
    
}
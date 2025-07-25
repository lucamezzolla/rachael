package com.rachael.api.wallet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import com.rachael.api.wallet.constant.PaymentType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PaymentResponse extends GenericResponse {

    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private LocalDate dueDate;
    private boolean paid;
    private PaymentType type;
    private Map<String, Object> extraDetails;
    
}
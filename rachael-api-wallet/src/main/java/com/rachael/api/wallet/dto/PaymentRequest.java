package com.rachael.api.wallet.dto;

import com.rachael.api.wallet.constant.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private BigDecimal amount;
    private LocalDate date;
    private LocalDate dueDate;
    private boolean paid;
    private PaymentType type;
    private Map<String, Object> extraDetails;
    
}
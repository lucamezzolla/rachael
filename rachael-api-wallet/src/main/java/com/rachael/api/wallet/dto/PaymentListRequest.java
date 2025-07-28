package com.rachael.api.wallet.dto;

import com.rachael.api.wallet.constant.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentListRequest {

    private int year;
    private PaymentType type;
    
}
package com.rachael.api.wallet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rachael.api.wallet.constant.PaymentType;
import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;		//prezzo

    private LocalDate date; 		// data del pagamento
    private LocalDate dueDate; 		// scadenza (facoltativa)

    private boolean paid;			//pagato

    @Enumerated(EnumType.STRING)
    private PaymentType type;		//tipo di pagamento

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> extraDetails;
    
}
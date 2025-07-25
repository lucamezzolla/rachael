package cutalab.rachael.backend.dto.wallet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import lombok.Data;

@Data
public class PaymentRequest {

    private BigDecimal amount;
    private LocalDate date;
    private LocalDate dueDate;
    private boolean paid;
    private PaymentType type;
    private Map<String, Object> extraDetails;
    
}
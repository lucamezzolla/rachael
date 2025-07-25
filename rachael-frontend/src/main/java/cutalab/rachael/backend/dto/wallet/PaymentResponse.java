package cutalab.rachael.backend.dto.wallet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import lombok.Data;

@Data
public class PaymentResponse extends GenericResponse {

    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private LocalDate dueDate;
    private boolean paid;
    private PaymentType type;
    private Map<String, Object> extraDetails;
    
}
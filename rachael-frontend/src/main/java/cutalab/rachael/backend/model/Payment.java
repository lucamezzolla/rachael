package cutalab.rachael.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import lombok.Data;

@Data
public class Payment {

    private Long id;
    private BigDecimal amount;		//prezzo
    private LocalDate date; 		// data del pagamento
    private LocalDate dueDate; 		// scadenza (facoltativa)
    private boolean paid;			//pagato
    private PaymentType type;		//tipo di pagamento
    private Map<String, Object> extraDetails;
    
}
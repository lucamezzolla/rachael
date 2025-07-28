package cutalab.rachael.backend.dto.wallet;

import java.util.List;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.model.Payment;
import lombok.Data;

@Data
public class PaymentListResponse extends GenericResponse {
	
    private List<Payment> payments;
    
}
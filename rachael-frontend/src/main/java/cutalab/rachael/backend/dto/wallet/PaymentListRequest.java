package cutalab.rachael.backend.dto.wallet;

import cutalab.rachael.base.ui.view.costant.PaymentConstant.PaymentType;
import lombok.Data;

@Data
public class PaymentListRequest {

    private int year;
    private PaymentType type;
    
}
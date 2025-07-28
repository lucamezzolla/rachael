package cutalab.rachael.backend.dto.user;

import lombok.Data;

@Data
public class ChangePasswordRequest {
	
    private Long id;
    private String password;
    
}
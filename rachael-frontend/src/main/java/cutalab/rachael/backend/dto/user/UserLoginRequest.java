package cutalab.rachael.backend.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {
	
    private String email;
    private String password;
    
}
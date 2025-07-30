package cutalab.rachael.backend.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
	
    private String name;
    private String email;

}
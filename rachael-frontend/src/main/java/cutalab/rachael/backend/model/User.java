package cutalab.rachael.backend.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String token;
    
}
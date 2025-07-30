package cutalab.rachael.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String token;
    private List<String> roles;

}
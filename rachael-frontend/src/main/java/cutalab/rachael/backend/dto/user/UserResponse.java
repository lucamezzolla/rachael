package cutalab.rachael.backend.dto.user;

import cutalab.rachael.backend.dto.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponse extends GenericResponse {
    private Long id;
    private String name;
    private String email;
    private String token;
}
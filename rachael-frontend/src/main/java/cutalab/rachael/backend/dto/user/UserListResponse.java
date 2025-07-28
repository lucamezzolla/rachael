package cutalab.rachael.backend.dto.user;

import java.util.List;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class UserListResponse extends GenericResponse {
    private List<User> users;
}
package cutalab.rachael.backend.dto.service;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.user.ChangePasswordRequest;
import cutalab.rachael.backend.dto.user.UserListResponse;
import cutalab.rachael.backend.dto.user.UserLoginRequest;
import cutalab.rachael.backend.dto.user.UserRequest;
import cutalab.rachael.backend.dto.user.UserResponse;
import cutalab.rachael.backend.model.User;

public interface UserService {

    UserResponse login(UserLoginRequest userLoginRequest);
    User getUserById(Long id);
    UserListResponse getAllUsers();
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request);
    GenericResponse changePassword(ChangePasswordRequest request);
    UserResponse deleteUser(Long id);
    
}
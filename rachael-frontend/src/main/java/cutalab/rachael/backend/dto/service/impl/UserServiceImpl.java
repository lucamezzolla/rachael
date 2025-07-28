package cutalab.rachael.backend.dto.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.service.UserService;
import cutalab.rachael.backend.dto.user.ChangePasswordRequest;
import cutalab.rachael.backend.dto.user.UserListResponse;
import cutalab.rachael.backend.dto.user.UserLoginRequest;
import cutalab.rachael.backend.dto.user.UserRequest;
import cutalab.rachael.backend.dto.user.UserResponse;
import cutalab.rachael.backend.model.User;
import cutalab.rachael.backend.proxy.UserProxy;

@Service
public class UserServiceImpl implements UserService {

    private final UserProxy userProxy;

    public UserServiceImpl(UserProxy userProxy) {
        this.userProxy = userProxy;
    }

    @Override
    public UserResponse login(UserLoginRequest userLoginRequest) {
        return userProxy.login(userLoginRequest).getBody();
    }

    @Override
    public UserListResponse getAllUsers() {
        return userProxy.getAllUsers().getBody();
    }

    @Override
    public User getUserById(Long id) {
        UserResponse response = userProxy.getUserById(id).getBody();
        if(response.getHttpStatus().equals(HttpStatus.OK)) {
        	var user = new User();
        	user.setId(response.getId());
        	user.setName(response.getName());
        	user.setEmail(response.getEmail());
        	return user;
        }
        return null;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        return userProxy.register(request).getBody();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        return userProxy.updateUser(id, request).getBody();
    }
    
    @Override
    public GenericResponse changePassword(ChangePasswordRequest request) {
        return userProxy.changePassword(request).getBody();
    }

    @Override
    public UserResponse deleteUser(Long id) {
        return userProxy.deleteUser(id).getBody();
    }
    
}
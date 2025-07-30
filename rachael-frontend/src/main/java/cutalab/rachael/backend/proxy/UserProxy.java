package cutalab.rachael.backend.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cutalab.rachael.backend.dto.GenericResponse;
import cutalab.rachael.backend.dto.user.ChangePasswordRequest;
import cutalab.rachael.backend.dto.user.UserListResponse;
import cutalab.rachael.backend.dto.user.UserLoginRequest;
import cutalab.rachael.backend.dto.user.UserRequest;
import cutalab.rachael.backend.dto.user.UserResponse;
import cutalab.rachael.backend.dto.user.UserUpdateRequest;
import cutalab.rachael.config.FeignAuthInterceptor;
import cutalab.rachael.config.FeignConfig;

@FeignClient(name = "rachael-api-user", configuration = {FeignAuthInterceptor.class, FeignConfig.class})
public interface UserProxy {

    @PostMapping("/api/user/login")
    ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request);

    @PostMapping("/api/user/register")
    ResponseEntity<UserResponse> register(@RequestBody UserRequest request);

    @GetMapping("/api/user")
    ResponseEntity<UserListResponse> getAllUsers();

    @GetMapping("/api/user/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id);

    @PutMapping("/api/user/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request);
    
    @PutMapping("/api/user/{id}/password")
    ResponseEntity<GenericResponse> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordRequest request);

    @DeleteMapping("/api/user/{id}")
    ResponseEntity<UserResponse> deleteUser(@PathVariable("id") Long id);
    
}
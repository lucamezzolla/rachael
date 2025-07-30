package com.rachael.api.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rachael.api.user.constant.ErrorMessages;
import com.rachael.api.user.constant.SuccessMessages;
import com.rachael.api.user.dto.GenericResponse;
import com.rachael.api.user.dto.UserListResponse;
import com.rachael.api.user.dto.UserLoginRequest;
import com.rachael.api.user.dto.UserPasswordRequest;
import com.rachael.api.user.dto.UserRequest;
import com.rachael.api.user.dto.UserResponse;
import com.rachael.api.user.model.User;
import com.rachael.api.user.repository.UserRepository;
import com.rachael.api.user.security.JwtUtil;
import com.rachael.api.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return UserResponse.builder()
                    .message(ErrorMessages.USER_NOT_FOUND)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return UserResponse.builder()
                    .message(ErrorMessages.INVALID_CREDENTIALS)
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .message(SuccessMessages.LOGIN_SUCCESS)
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .timestamp(LocalDateTime.now())
            .httpStatus(HttpStatus.CREATED)
            .message(SuccessMessages.USER_CREATED)
            .build();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return UserResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ErrorMessages.USER_NOT_FOUND_WITH_ID(id))
                    .build();
        }
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        User updated = userRepository.save(user);
        return UserResponse.builder()
                .id(updated.getId())
                .email(updated.getEmail())
                .name(updated.getName())
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.USER_UPDATED)
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return UserResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ErrorMessages.USER_NOT_FOUND_WITH_ID(id))
                    .build();
        }
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.USER_FOUND)
                .build();
    }

    @Override
    public UserListResponse getAllUsers() {
        List<UserResponse> response = userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .build())
                .collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        for(UserResponse res : response) {
        	var user = new User();
        	user.setId(res.getId());
        	user.setName(res.getName());
        	users.add(user);
        }
        return UserListResponse.builder()
                .users(users)
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.USERS_LIST_FOUND)
                .build();
    }

    @Override
    public GenericResponse deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return GenericResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ErrorMessages.USER_NOT_FOUND_WITH_ID(id))
                    .build();
        }
        userRepository.delete(user);
        return GenericResponse.builder()
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.USER_DELETED)
                .build();
    }

    @Override
    public GenericResponse changePassword(Long id, UserPasswordRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return GenericResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ErrorMessages.USER_NOT_FOUND_WITH_ID(id))
                    .build();
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return GenericResponse.builder()
                .timestamp(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessages.PASSWORD_EMPTY)
                .build();
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return GenericResponse.builder()
            .timestamp(LocalDateTime.now())
            .httpStatus(HttpStatus.OK)
            .message(SuccessMessages.PASSWORD_CHANGED) 
            .build();
    }

}

package com.rachael.api.user.dto;

import java.util.List;

import com.rachael.api.user.model.User;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserListResponse extends GenericResponse {
	
    private List<User> users;
    
}
package com.rachael.api.user.dto;

import lombok.Data;

@Data
public class UserRequest {
	
    private String name;
    private String email;
    private String password;

}
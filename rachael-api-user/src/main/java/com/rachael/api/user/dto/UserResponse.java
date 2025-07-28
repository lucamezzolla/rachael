package com.rachael.api.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserResponse extends GenericResponse {
    private Long id;
    private String name;
    private String email;
    private String token;
}
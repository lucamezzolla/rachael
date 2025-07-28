package com.rachael.api.user.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GenericResponse {

	private LocalDateTime timestamp;
    private String message;
    private HttpStatus httpStatus;
    
}
package cutalab.rachael.backend.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GenericResponse {

	private LocalDateTime timestamp;
    private String message;
    private HttpStatus httpStatus;
    
}
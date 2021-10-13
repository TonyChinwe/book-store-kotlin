package com.phisoft.bookstorekotlin.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Class for messages when an exception is thrown
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    /**
     * The status code of the error. like 401,402,500 etc
     */
    private HttpStatus httpStatus;
    /**
     * The error  message displayed to the user
     */
    private String message;

}

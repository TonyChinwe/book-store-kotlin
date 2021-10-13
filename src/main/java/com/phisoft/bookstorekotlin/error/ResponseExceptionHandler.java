package com.phisoft.bookstorekotlin.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class intercepts all the request coming to the and going out the controller classes
 * for possible errors.
 */
@ControllerAdvice
@ResponseStatus
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This one sends a custom error message when book-not-found exception is thrown
     * @param exception book not found exception
     * @param webRequest the web request
     * @return response entity with the custom message
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorMessage> bookNotFoundException(BookNotFoundException exception, WebRequest webRequest){
      ErrorMessage errorMessage=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * This one sends a custom error message when not-authorized-exception exception is thrown
     * @param exception book not found exception
     * @param webRequest the web request
     * @return response entity with the custom message
     */
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorMessage> notAuthorizedException(NotAuthorizedException exception, WebRequest webRequest){
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.UNAUTHORIZED,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.EXPECTATION_FAILED,details.toString());

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorMessage);


    }
}

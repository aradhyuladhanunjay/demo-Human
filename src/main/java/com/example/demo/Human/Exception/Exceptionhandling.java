package com.example.demo.Human.Exception;

import com.example.demo.Human.HumanDTO.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class Exceptionhandling {

    @ExceptionHandler(Responsenotfound.class)
    public ResponseEntity<String> exceptionhandling(Responsenotfound ex){
     return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionhandling2(Exception ex, WebRequest webRequest){

        ErrorDetails error = new ErrorDetails(ex.getMessage(), new Date(), webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

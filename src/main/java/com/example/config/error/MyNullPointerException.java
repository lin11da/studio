package com.example.config.error;

import com.example.config.error.pojo.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/15 12:02
 */
@ControllerAdvice
public class MyNullPointerException {
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(NullPointerException ex, WebRequest request) {
        String message = "字段不能为空";
        Boolean success = false;
        ErrorMessage errorObj = new ErrorMessage(new Date(), message,
                request.getDescription(false),"500",success);
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

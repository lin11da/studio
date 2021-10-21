package com.example.config.error;

import cn.dev33.satoken.exception.NotLoginException;
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
 * @date 2021/9/15 11:03
 */
@ControllerAdvice
public class MytokenExceptionHandler {
    @ExceptionHandler(NotLoginException.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(NotLoginException ex, WebRequest request) {
        String type = ex.getType();

        String code = null;
        Boolean success = true;
        if ("-1".equals(type)||"-2".equals(type)||"-3".equals(type)){
            code = "201";
            success = false;
        }

        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage(),
                request.getDescription(false),code,success);
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.example.config.error;

import cn.dev33.satoken.exception.NotRoleException;
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
 * @date 2021/9/22 19:54
 */
@ControllerAdvice
public class MyNotRoleException {
    @ExceptionHandler(NotRoleException.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(NotRoleException ex, WebRequest request) {
        String message = "没有该角色权限";
        Boolean success = false;
        ErrorMessage errorObj = new ErrorMessage(new Date(), message,
                request.getDescription(false),"500",success);
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

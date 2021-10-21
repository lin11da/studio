package com.example.config.error.pojo;

import java.util.Date;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/15 11:02
 */
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String details;
    private String code;
    private Boolean success;
    public ErrorMessage(){}
    public ErrorMessage(Date timestamp, String message, String details,String code,Boolean success) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.code = code;
        this.success = success;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

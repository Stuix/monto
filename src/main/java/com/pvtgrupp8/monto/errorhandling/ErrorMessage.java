package com.pvtgrupp8.monto.errorhandling;

import javax.persistence.Entity;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ErrorMessage {

    private Date timestamp;
    private List<String> message;
    private String details;



    public ErrorMessage(){};

    public ErrorMessage(Date timestamp, Set<ConstraintViolation<?>> violations, String details) {
        this.timestamp = timestamp;
        this.details = details;
        message = new ArrayList<>();
        for(ConstraintViolation<?> violation: violations) {
        message.add(violation.getMessage());
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}

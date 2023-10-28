package br.com.erudio.restwithspringbootandjavaerudio.exceptions;

import java.util.Date;
import java.io.Serializable;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

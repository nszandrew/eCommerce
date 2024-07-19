package com.ecommerce.firstversion.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private Date timestamp;
    private String datails;


    public ExceptionResponse(Date date, String message, String datails) {
        this.timestamp = date;
        this.message = message;
        this.datails = datails;
    }
}

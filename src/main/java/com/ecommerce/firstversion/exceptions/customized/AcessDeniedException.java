package com.ecommerce.firstversion.exceptions.customized;

public class AcessDeniedException extends RuntimeException {

    public AcessDeniedException(String message) {
        super(message);
    }
}

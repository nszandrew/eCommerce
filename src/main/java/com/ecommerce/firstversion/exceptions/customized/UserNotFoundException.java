package com.ecommerce.firstversion.exceptions.customized;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}

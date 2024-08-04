package com.ecommerce.firstversion.exceptions.customized;

public class WeakPasswordException extends RuntimeException{

    public WeakPasswordException(String message){
        super(message);
    }
}

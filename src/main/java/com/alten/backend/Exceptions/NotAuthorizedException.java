package com.alten.backend.Exceptions;

public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException(String msg){
        super(msg);
    }
}

package com.framework.Exception;

public class InvalidPageException extends InternalException {
    public InvalidPageException(String name) {
        super("Invalid page :" + name);
    }
}

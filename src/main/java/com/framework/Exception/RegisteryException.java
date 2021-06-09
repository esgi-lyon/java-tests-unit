package com.framework.Exception;

public class RegisteryException extends InternalException {
    public RegisteryException(String name, String message) {
        super((message != null ? message : "Invalid service registration :") + " " + name);
    }
}

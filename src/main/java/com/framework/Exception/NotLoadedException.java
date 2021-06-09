package com.framework.Exception;

public class NotLoadedException extends RegisteryException {
    public NotLoadedException(String name) {
        super("Not loaded service : ", name);
    }
}

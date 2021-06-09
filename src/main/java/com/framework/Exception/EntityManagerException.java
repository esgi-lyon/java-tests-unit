package com.framework.Exception;

public class EntityManagerException extends InternalException {
    public EntityManagerException(Exception e) {
        super("Error during entity manager operation / transaction");
        e.printStackTrace();
    }
}

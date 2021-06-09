package com.app.Cli;

import com.framework.Exception.EntityManagerException;

public class Main {

    public static void main(String[] args) throws EntityManagerException {
        new ConsoleService(args);
    }
}

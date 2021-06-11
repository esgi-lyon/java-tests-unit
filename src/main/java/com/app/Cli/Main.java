package com.app.Cli;

import com.app.InjectionModules;
import com.framework.Exception.EntityManagerException;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws EntityManagerException {
        Injector injector = InjectionModules.getFilledInjector();
        BuyEventProcess buyEventProcess = injector.getInstance(BuyEventProcess.class);
        buyEventProcess.process(args);
    }
}

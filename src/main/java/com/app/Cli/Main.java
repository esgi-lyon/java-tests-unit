package com.app.Cli;

import com.app.Modules.CoreModule;
import com.app.Modules.ServiceModule;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManagerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) throws EntityManagerException {
        Injector injector = Guice.createInjector(new CoreModule(), new EntityManagerModule(), new ServiceModule());
        BuyEventProcess buyEventProcess = injector.getInstance(BuyEventProcess.class);
        buyEventProcess.process(args);
    }
}

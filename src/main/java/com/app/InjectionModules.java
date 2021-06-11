package com.app;

import com.app.Modules.CoreModule;
import com.app.Modules.ServiceModule;
import com.framework.Services.Dao.EntityManagerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectionModules {
    public static Injector getFilledInjector() {
        return Guice.createInjector(new CoreModule(), new EntityManagerModule(), new ServiceModule());
    }
}

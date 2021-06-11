package com.exam2;

import com.exam1.Modules.CoreModule;
import com.exam1.Modules.ServiceModule;
import com.framework.Services.Dao.EntityManagerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectionModules {
    public static Injector getFilledInjector() {
        return Guice.createInjector(new CoreModule(), new EntityManagerModule(), new ServiceModule());
    }
}

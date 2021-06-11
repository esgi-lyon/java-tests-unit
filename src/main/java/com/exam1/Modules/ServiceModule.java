package com.exam1.Modules;

import com.exam1.Service.MusicalEventService;
import com.exam1.Service.UserService;
// import com.framework.Services.Dao.EntityManagerFactory;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class);
        bind(MusicalEventService.class);
    }
}
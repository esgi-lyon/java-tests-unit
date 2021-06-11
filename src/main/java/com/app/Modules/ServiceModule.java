package com.app.Modules;

import com.app.Service.MusicalEventService;
import com.app.Service.UserService;
// import com.framework.Services.Dao.EntityManagerFactory;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class);
        bind(MusicalEventService.class);
    }
}
package com.exam2.Modules;

import com.exam2.Service.HotelService;
import com.exam2.Service.UserService;
// import com.framework.Services.Dao.EntityManagerFactory;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class);
        bind(HotelService.class);
    }
}
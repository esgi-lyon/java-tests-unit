package com.framework.Services.Dao;

import com.google.inject.AbstractModule;

public class EntityManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EntityManagerFactory.class)
                .toInstance(new EntityManagerFactory());
    }
}

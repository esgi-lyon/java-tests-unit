package com.exam2.Modules;

import com.exam2.Cli.HotelProcess;
import com.google.inject.AbstractModule;

/**
 * Inject main classes (UI / CLI)
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HotelProcess.class);
    }
}

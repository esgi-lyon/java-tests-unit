package com.exam2.Modules;

import com.exam1.Cli.BuyEventProcess;
import com.google.inject.AbstractModule;

/**
 * Inject main classes (UI / CLI)
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BuyEventProcess.class);
        // TODO refacto UI
        // bind(MainFrame.class);
    }
}

package com.app.Modules;

import com.app.Cli.BuyEventProcess;
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

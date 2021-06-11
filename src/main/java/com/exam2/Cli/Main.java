package com.exam2.Cli;

import com.exam2.InjectionModules;
import com.framework.Exception.EntityManagerException;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws EntityManagerException {
        Injector injector = InjectionModules.getFilledInjector();
        HotelProcess hotelProcess = injector.getInstance(HotelProcess.class);
        hotelProcess.process(args);
    }
}

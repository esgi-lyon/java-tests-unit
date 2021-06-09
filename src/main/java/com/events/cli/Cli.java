package com.events.cli;

import com.framework.Exception.EntityManagerException;

import java.util.HashMap;

public class Cli {

    public static void main(String[] args) throws EntityManagerException {
        HashMap<String, String> argMap = Cli.parseArgs(args);
        new ConsoleService(argMap.get("name"), argMap.get("event"));
    }

    public static HashMap<String, String> parseArgs(String[] args)  {
        HashMap<String, String> argMap = new HashMap<>();
        String storeKey = null;

        if (args.length < 1) {
            Cli.printHelp();
            throw new IllegalArgumentException("No argument passed");
        }

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                storeKey = arg.substring(1, args.length);
            } else if (storeKey != null) {
                argMap.put(storeKey, arg);
                storeKey = null;
            } else {
                Cli.printHelp();
                throw new IllegalArgumentException("Review argument passed");
            }
        }

        return argMap;
    }

    protected static void printHelp() {
        System.out.println("Help: \n usage: ./cli.jar -name Jean -event Soenda");
    }
}

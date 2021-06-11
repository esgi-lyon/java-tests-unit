package com.framework.Utils;

import java.util.HashMap;

public class ArgsUtils {

    public static HashMap<String, String> toMap(String[] args)  {
        HashMap<String, String> argMap = new HashMap<>();

        if (args.length < 1) {
            throw new IllegalArgumentException("No argument passed");
        }

        String storeKey = null;

        for (String arg : args) {
            if (arg.charAt(0) == '-' && storeKey == null) {
                storeKey = arg.substring(1);
                continue;
            }

            if (storeKey != null) {
                argMap.put(storeKey, arg);
                storeKey = null;
            } else {
                throw new IllegalArgumentException("Review argument passed");
            }
        }

        return argMap;
    }
}

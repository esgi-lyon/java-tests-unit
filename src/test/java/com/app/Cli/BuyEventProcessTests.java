package com.app.Cli;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

public class BuyEventProcessTests {

    @Test
    public void parseArgs() {
        HashMap<String, String> map = BuyEventProcess.parseArgs(BuyEventProcessTests.getArgs());
        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
        // Empty args
        assertThrows(IllegalArgumentException.class, () -> { BuyEventProcess.parseArgs(new String[]{}); });
    };

    protected static String[] getArgs(){
        return new String[]{"-event", "Soenda festival", "-name", "Loic"};
    }
}

package com.app.Cli;

import com.framework.Exception.EntityManagerException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

public class ConsoleServiceTests {

    @Test
    public void parseArgs() {
        HashMap<String, String> map = ConsoleService.parseArgs(ConsoleServiceTests.getArgs());
        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
        // Empty args
        assertThrows(IllegalArgumentException.class, () -> { ConsoleService.parseArgs(new String[]{}); });
    };

    protected static String[] getArgs(){
        return new String[]{"-event", "Soenda festival", "-name", "Loic"};
    }
}

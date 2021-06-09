package com.app.Cli;

import com.framework.Exception.EntityManagerException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class ConsoleServiceTests {

    @Test
    public static void parseArgs() throws EntityManagerException {
        ConsoleService consoleService = new ConsoleService();
        HashMap<String, String> map = consoleService.parseArgs(new String[]{"-event", "Soenda festival", "-name", "Loic"});

        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
    };
}

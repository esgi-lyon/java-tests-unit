package com.events.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

public class CliTests {

    @Test
    public static void main() {
        assertTrue(1 == 1);
    }

    @Test
    public static void parseArgs() {
        HashMap<String, String> map = Cli.parseArgs(new String[]{"-event", "Soenda festival", "-name", "Loic"});

        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
    };
}

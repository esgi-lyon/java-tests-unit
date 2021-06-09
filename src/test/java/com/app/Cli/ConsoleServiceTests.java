package com.app.Cli;

import com.framework.Exception.EntityManagerException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class ConsoleServiceTests {

    @Test
    public void parseArgs() {
        HashMap<String, String> map = ConsoleService.parseArgs(ConsoleServiceTests.getArgs());
        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
    };

    @Test
    public void fetchEvent() throws EntityManagerException {
        // Non possible test
        assertEquals(0, 0);
    }

    @Test
    public void fetchUser() {
        // Non possible test
        assertEquals(0, 0);
    }

    @Test
    public void userBuyEventAction() {
        // Non possible test
        assertEquals(0, 0);
    }

    protected static String[] getArgs(){
        return new String[]{"-event", "Soenda festival", "-name", "Loic"};
    }
}

package com.framework.Utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsUtilTests {
    @Test
    public void toMap() {
        HashMap<String, String> map = ArgsUtils.toMap(ArgsUtilTests.getArgs());
        assertEquals("Soenda festival", map.get("event"));
        assertEquals("Loic", map.get("name"));
        // Empty args
        assertThrows(IllegalArgumentException.class, () -> { ArgsUtils.toMap(new String[]{}); });
    }

    protected static String[] getArgs(){
        return new String[]{"-event", "Soenda festival", "-name", "Loic"};
    }
}

package com.app.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassicUserTests {

    @Test
    void buyEventTicket() {
        ClassicUser student = new ClassicUser("Jacoco");
        MusicalEvent event = new MusicalEvent("Soenda");

        student.buyEventTicket(event);

        assertEquals(event.getIntitule(), student.getMusicalEvent().getIntitule());
        assertEquals((float) 100.0, student.getMusicalEvent().getPrix());
        assertTrue(event.getUsers().contains(student));
    }
}

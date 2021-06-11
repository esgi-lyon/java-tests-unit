package com.exam1.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTests {

    @Test
    void buyEventTicket() {
        Student student = new Student("Jacoco");
        MusicalEvent event = new MusicalEvent("Soenda");

        student.buyEventTicket(event);

        assertEquals(event.getIntitule(), student.getMusicalEvent().getIntitule());
        assertEquals((float) 70.00, student.getMusicalEvent().getPrix());
        assertTrue(event.getUsers().contains(student));

        for (int i = 0; i < 19; i++) {
            student.buyEventTicket(new MusicalEvent("Soenda" + i));
        }

        assertEquals((float) 0, student.getMusicalEvent().getPrix());

        student.buyEventTicket(new MusicalEvent("Soenda reset price"));
        assertEquals((float) 70.00, student.getMusicalEvent().getPrix());
    }
}

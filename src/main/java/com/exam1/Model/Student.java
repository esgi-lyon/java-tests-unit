package com.exam1.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {
    public Student() {}

    public Student(String name) {
        super(name);
    }

    @Column(name = "event_count")
    int eventCount = 0;

    @Override
    public void buyEventTicket(MusicalEvent event) {
        this.eventCount += 1;

        if (eventCount >= 20) {
            event.setPrix(0);
            eventCount = 0;
        } else {
            event.setPrix(event.getPrix() * (float) 0.7);
        }

        this.setMusicalEvent(event);
        event.addUser(this);
    }

}

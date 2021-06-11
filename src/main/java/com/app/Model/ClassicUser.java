package com.app.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "classic_user")
public class ClassicUser extends User {
    public ClassicUser(String name) {
        super(name);
    }

    @Override
    public void buyEventTicket(MusicalEvent event) {
        event.addUser(this);
        this.setMusicalEvent(event);
    }

    public ClassicUser() {}
}

package com.exam2.Model;

import com.exam1.Model.MusicalEvent;
import com.exam1.Model.User;

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

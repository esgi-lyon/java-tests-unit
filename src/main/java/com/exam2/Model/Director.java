package com.exam2.Model;

import com.exam2.Model.Hotel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "director")
public class Director extends User {
    public Director(String name) {
        super(name);
    }

    public Director() {}
}

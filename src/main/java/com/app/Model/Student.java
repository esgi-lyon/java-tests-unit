package com.app.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {
    public Student(String name) {
        super(name);
    }

    public Student() {}
}

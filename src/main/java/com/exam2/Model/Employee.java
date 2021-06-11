package com.exam2.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends User {
    public Employee() {}

    public Employee(String name) {
        super(name);
    }

}

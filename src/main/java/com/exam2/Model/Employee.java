package com.exam2.Model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends User {

    @Column(name = "salary")
    int salary;

    @Column(name = "experience")
    int experience;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="director_id", nullable = false)
    Director director;

    public Employee() {}

    public Employee(String name, String lastName, int salary, int experience, Director director) {
        super(name, lastName);
        this.salary = salary;
        this.experience = experience;
        this.director = director;
    }
}

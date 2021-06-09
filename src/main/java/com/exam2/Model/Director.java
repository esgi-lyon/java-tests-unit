package com.exam2.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "director")
public class Director extends User {

    @OneToMany(mappedBy="director", fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "director", fetch = FetchType.EAGER)
    private Hotel hotel;

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Director(String name, String lastName) {
        super(name, lastName);
    }

    public Director() {}
}

package com.exam2.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Hotel")
public class Hotel extends AbstractEntity {

    @Column(name = "name")
    String name;

    @Column(name = "phone")
    String phone;

    @Column(name = "address")
    String address;

    @OneToOne(fetch = FetchType.EAGER)
    Director director;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {}

    public Hotel(String name, String phone, String address) {
    	this.name = name;
    	this.phone = phone;
    	this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String intitule) {
        this.name = intitule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return getId() + ", " + name + ", " + phone;
    }

    @Override
    public String toString(boolean list) {
        return
            getId() + ", "
                + getName() + ", "
                + getPhone()
            ;
    }
}

package com.exam2.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room extends AbstractEntity {

    @Column(name = "number")
    int number;

    @Column(name = "floor")
    int floor;

    @Column(name = "prix")
    float prix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id", nullable = false)
    Hotel hotel;

    public Room() {}

    public Room(int intitule, int floor) {
    	this.number = intitule;
    	this.floor = floor;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int intitule) {
        this.number = intitule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float price) {
        prix = price;
    }

    @Override
    public String toString() {
        return getId() + ", " + prix;
    }

    @Override
    public String toString(boolean list) {
        return
            getId() + ", "
                + getPrix()
            ;
    }
}

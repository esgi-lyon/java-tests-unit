package com.exam2.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "Hotel")
public class Hotel extends AbstractEntity {

    public enum Status {
        failed, planned, processing, done
    }

    @Column(name = "intitule")
    String intitule;

    @Column(name = "prix")
    float prix;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    Director director;

    public final double basePrice = 100.00;

    public Hotel() {}

    public Hotel(String intitule) {
    	this.intitule = intitule;
    	this.prix = (float) basePrice;
    	this.status = Status.planned;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float price) {
        prix = price;
    }

    @Override
    public String toString() {
        return getId() + ", " + intitule + ", " + prix;
    }

    @Override
    public String toString(boolean list) {
        return
            getId() + ", "
                + getIntitule() + ", "
                + getPrix()
            ;
    }
}

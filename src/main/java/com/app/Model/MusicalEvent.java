package com.app.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Service")
public class MusicalEvent extends AbstractEntity {

    public enum Status {
        failed, planned, processing, done
    }

    @Column(name = "intitule")
    String intitule;

    @Column(name = "prix")
    float prix;

    @Enumerated(EnumType.STRING)
    Status status;

    @OneToMany(mappedBy = "musicalEvent")
    Set<User> users = new HashSet<>();

    public final double basePrice = 100.00;

    public MusicalEvent() {}

    public MusicalEvent(String intitule) {
    	this.intitule = intitule;
    	this.prix = (float) basePrice;
    	this.status = Status.processing;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> user) {
        this.users = user;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return getId() + ", " + intitule;
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

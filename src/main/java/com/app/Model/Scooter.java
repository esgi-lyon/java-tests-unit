package com.app.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "Service")
public class Scooter extends AbstractEntity {

    public enum Status {
        failed, processing, done
    }

    @Column(name = "intitule")
    String intitule;

    @Column(name = "prix")
    float prix;

    @Enumerated(EnumType.STRING)
    Status status;

    @OneToOne(mappedBy = "scooter")
    User user;

    public Scooter() {}

    public Scooter(String intitule, float prix) {
    	this.intitule = intitule;
    	this.prix = prix;
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

    public void setPrix(float prixHT) {
        this.prix = prixHT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float prixHT() {
    	return prix;
    }

    public Scooter process(User user) {
        this.setUser(user);
        this.setStatus(Status.processing);

        return this;
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

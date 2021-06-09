package com.events.Model;

import com.framework.Model.AbstractEntity;

import javax.persistence.*;

/**
 * Class used to reuse simple properties for
 * Client and Builders which are the two actors of the app
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MusicalEvent musicalEvent;

    /**
     * Default
     */
    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public MusicalEvent getService() {
        return musicalEvent;
    }

    public void setService(MusicalEvent musicalEvent) {
        this.musicalEvent = musicalEvent;
    }

    /**
     * Java default toString
     * @return string
     */
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String toString(boolean list) {
        return getId() + ", " + getName();
    }
}

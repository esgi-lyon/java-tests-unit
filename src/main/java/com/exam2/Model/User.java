package com.exam2.Model;

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

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User managed;

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

    /**
     * Java default toString
     * @return string
     */
    @Override
    public String toString() {
        return getId() + ", " + this.name;
    }

    @Override
    public String toString(boolean list) {
        return getId() + ", " + getName();
    }
}

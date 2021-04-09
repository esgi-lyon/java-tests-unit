package com.app.Model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

enum Status {
    failed, processing, done
}

@Entity
@Table(name = "Service")
public class Service extends AbstractEntity {

    @Column(name = "intitule")
    String intitule;

    @Column(name = "prix")
    float prix;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToMany()
    @JoinTable(name = "article_magasin",
            joinColumns = { @JoinColumn(name = "fk_article") },
            inverseJoinColumns = { @JoinColumn(name = "fk_magasin") })
    Set<Magasin> magasins = new HashSet<>();

    public Service() {}

    public Service(String intitule, float prix, int stock) {
    	this.intitule = intitule;
    	this.prix = prix;
    	this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int quantiteEnStock) {
        this.stock = quantiteEnStock;
    }

    public float getTVA() {
        return TVA;
    }

    public void setTVA(float TVA) {
        this.TVA = TVA;
    }

    public Set<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(Set<Magasin> magasins) {
        this.magasins = magasins;
    }

    // Entity logical actions

    public void approvisionner(int qte) {
    	stock +=qte;
    }
    
    public boolean vendre(int qte) {
    	if(qte > stock) {
    		return false;
    	}else {
    		stock -=qte;
    		return true;
    	}
    }

    public float prixHT() {
    	return prix;
    }
    
    public float prixTTC() {
    	float ttc = Math.round((prix *TVA) * 100) / 100;
    	return ttc;
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
                + getPrix() + ", "
                + prixTTC() + ", "
                + getStock()
            ;
    }
}

package com.banque.persistance.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nom", length = 50)
    private String nom;

    public String getNom() {
        return nom;
    }
    public void setNom(String n) {
        this.nom = n;
    }
    @Column(name = "prenom", length = 50)
    private String prenom;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String p) {
        this.prenom = p;
    }

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    public List<Account> accounts = new ArrayList<>();
}

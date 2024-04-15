package com.banque.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "compte")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "numero", length = 50)
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    @Column(name = "credit")
    private BigDecimal credit;

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idClient")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client c) {
        this.client = c;
    }

    @Override
    public String toString(){
        return "Compte [id =" + id + ", numero =" + numero + ", credit =" + credit;
    }

    public Integer getId_client() {
        return (client != null)
                ? client.getId()
                : null;
    }
}

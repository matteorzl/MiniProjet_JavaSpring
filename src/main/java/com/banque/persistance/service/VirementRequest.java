package com.banque.persistance.service;

public class VirementRequest {
    private Integer expediteur;
    private Integer destinataire;
    private Double montant;

    public Integer getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Integer expediteur) {
        this.expediteur = expediteur;
    }

    public Integer getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Integer destinataire) {
        this.destinataire = destinataire;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
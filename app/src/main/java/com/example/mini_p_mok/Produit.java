package com.example.mini_p_mok;

public class Produit {
    String nom_p, categorie_p,prix_p;

    public Produit(String nom_p, String categorie_p, String prix_p) {
        this.nom_p = nom_p;
        this.categorie_p = categorie_p;
        this.prix_p = prix_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getCategorie_p() {
        return categorie_p;
    }

    public String getPrix_p() {
        return prix_p;
    }
}

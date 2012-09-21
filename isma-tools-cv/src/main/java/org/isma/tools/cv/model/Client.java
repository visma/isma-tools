package org.isma.tools.cv.model;

public class Client {
    private final String nom;
    private final String fonction;
    private final String equipe;

    public Client(String nom, String fonction, String equipe) {
        this.nom = nom;
        this.fonction = fonction;
        this.equipe = equipe;
    }

    public String getNom() {
        return nom;
    }

    public String getFonction() {
        return fonction;
    }

    public String getEquipe() {
        return equipe;
    }
}

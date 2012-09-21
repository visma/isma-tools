package org.isma.tools.cv.model;

public class Langue {
    private final String nom;
    private final String niveau;

    public Langue(String nom, String niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public String getNiveau() {
        return niveau;
    }
}

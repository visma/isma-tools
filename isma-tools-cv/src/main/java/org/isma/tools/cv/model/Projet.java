package org.isma.tools.cv.model;

public class Projet {
    private final String nom;
    private final String texte;

    public Projet(String nom, String texte) {
        this.nom = nom;
        this.texte = texte;
    }

    public String getNom() {
        return nom == null ? "" : nom;
    }

    public String getTexte() {
        return texte;
    }
}

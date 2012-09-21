package org.isma.tools.cv.model;

public class Formation {
    private final String nom;
    private final String annee;
    private final String specialisation;

    public Formation(String nom, String annee, String specialisation) {
        this.nom = nom;
        this.annee = annee;
        this.specialisation = specialisation;
    }

    public String getNom() {
        return nom;
    }

    public String getAnnee() {
        return annee;
    }

    public String getSpecialisation() {
        return specialisation;
    }
}

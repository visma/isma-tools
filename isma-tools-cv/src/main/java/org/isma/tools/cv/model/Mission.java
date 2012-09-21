package org.isma.tools.cv.model;

public class Mission {
    private final String poste;
    private final String duree;
    private final String dateDebut;
    private final String dateFin;
    private final String type;
    private final String effectifs;
    private final String texte;

    public Mission(String poste, String duree, String dateDebut, String dateFin, String type, String effectifs, String texte) {
        this.poste = poste;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.effectifs = effectifs;
        this.texte = texte;
    }

    public String getPoste() {
        return poste;
    }

    public String getDuree() {
        return duree;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getType() {
        return type;
    }

    public String getEffectifs() {
        return effectifs;
    }

    public String getTexte() {
        return texte;
    }

    public String getPeriode() {
        return getDateDebut() + " - " + getDateFin();
    }
}

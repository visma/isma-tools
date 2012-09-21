package org.isma.tools.cv.model;

import java.util.List;

public class Profil {
    private final String titre;
    private final String description;
    private final List<Competence> competences;

    public Profil(String titre, String description, List<Competence> competences) {
        this.titre = titre;
        this.description = description;
        this.competences = competences;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public List<Competence> getCompetences() {
        return competences;
    }
}

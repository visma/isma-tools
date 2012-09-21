package org.isma.tools.cv.model;

import java.util.List;

public class Cv {
    private final Weight weight;
    private final Candidat candidat;
    private final List<Formation> formations;
    private final List<PageExperiences> pagesExperiences;
    private List<Langue> langues;

    public Cv(Weight weight, Candidat candidat, List<Formation> formations, List<PageExperiences> pagesExperiences, List<Langue> langues) {
        this.weight = weight;
        this.candidat = candidat;
        this.formations = formations;
        this.pagesExperiences = pagesExperiences;
        this.langues = langues;
    }



    public Weight getWeight() {
        return weight;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public List<PageExperiences> getPagesExperiences() {
        return pagesExperiences;
    }

    public List<Langue> getLangues() {
        return langues;
    }
}

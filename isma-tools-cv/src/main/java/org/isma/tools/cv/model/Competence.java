package org.isma.tools.cv.model;

import java.util.List;

public class Competence {
    private final String intitule;
    private final List<String> details;

    public Competence(String intitule, List<String> details) {
        this.intitule = intitule;
        this.details = details;
    }

    public String getIntitule() {
        return intitule;
    }

    public List<String> getDetails() {
        return details;
    }
}

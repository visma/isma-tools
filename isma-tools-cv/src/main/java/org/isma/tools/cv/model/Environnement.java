package org.isma.tools.cv.model;

import java.util.List;

public class Environnement {
    private List<String> details;

    public Environnement(List<String> details) {
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }
}

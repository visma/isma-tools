package org.isma.tools.cv.model;

import java.util.List;

public class PageExperiences {
    private final List<Experience> experiences;

    public PageExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }
}

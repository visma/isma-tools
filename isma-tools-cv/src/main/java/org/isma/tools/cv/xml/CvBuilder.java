package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.*;
import org.jdom.Element;

import java.util.List;


public class CvBuilder extends Builder<Cv> {
    private final CandidatBuilder candidatBuilder = new CandidatBuilder();
    private final FormationsBuilder formationsBuilder = new FormationsBuilder();
    private final PagesExperiencesBuilder pagesExperiencesBuilder = new PagesExperiencesBuilder();
    private final LanguesBuilder languesBuilder = new LanguesBuilder();

    private enum Tags {
        CANDIDAT,
        FORMATIONS,
        EXPERIENCES,
        LANGUES;

        String getName() {
            return name().toLowerCase();
        }
    }

    public Cv build(Element cvElement) {
        Candidat candidat = candidatBuilder.build(cvElement.getChild(Tags.CANDIDAT.getName()));
        List<Formation> formations = formationsBuilder.build(cvElement.getChild(Tags.FORMATIONS.getName()));
        List<PageExperiences> pagesExperiences = pagesExperiencesBuilder.build(cvElement.getChild(Tags.EXPERIENCES.getName()));
        List<Langue> langues = languesBuilder.build(cvElement.getChild(Tags.LANGUES.getName()));

        String cvweight = getRequiredAttributeValue(cvElement, "cvweight");
        Weight weight;
        if (cvweight.equals("heavy")){
            weight = Weight.HEAVY;
        }else if (cvweight.equals("light")){
            weight = Weight.LIGHT;
        }else{
            throw new RuntimeException("weight not initialized");
        }
        return new Cv(weight, candidat, formations, pagesExperiences, langues);
    }
}

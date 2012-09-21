package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Competence;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class CompetencesBuilder extends Builder<List<Competence>> {
    public List<Competence> build(Element element) {
        List<Competence> competences = new ArrayList<Competence>();
        for (Object obj : element.getChildren("competence")) {
            competences.add(buildCompetence((Element) obj));
        }
        return competences;
    }

    private Competence buildCompetence(Element element) {
        List<String> details = new ArrayList<String>();
        for (Object obj : element.getChildren("detail")) {
            Element detail = (Element) obj;
            details.add(detail.getText());
        }
        String intitule = getRequiredAttributeValue(element, "intitule");
        return new Competence(intitule, details);
    }
}

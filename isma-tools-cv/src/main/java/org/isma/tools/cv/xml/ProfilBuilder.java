package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Competence;
import org.isma.tools.cv.model.Profil;
import org.jdom.Element;

import java.util.List;

public class ProfilBuilder extends Builder<Profil> {
    public Profil build(Element element) {
        List<Competence> competences = new CompetencesBuilder().build(element.getChild("competences"));
        return new Profil(
                getRequiredAttributeValue(element, "titre"),
                getRequiredAttributeValue(element, "description"),
                competences);
    }
}

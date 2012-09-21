package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Projet;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class ProjetsBuilder extends Builder<List<Projet>> {

    public List<Projet> build(Element projetsElem) {
        List<Projet> projets = new ArrayList<Projet>();
        for (Element projetElem : (List<Element>) projetsElem.getChildren("projet")) {
            projets.add(buildProjet(projetElem));
        }
        return projets;
    }

    private Projet buildProjet(Element projetElem) {
        return new Projet(
                getOptionnalAttributeValue(projetElem, "nom"),
                projetElem.getChild("texte").getText());
    }
}

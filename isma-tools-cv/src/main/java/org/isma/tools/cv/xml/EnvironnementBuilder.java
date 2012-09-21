package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Environnement;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class EnvironnementBuilder extends Builder<Environnement> {
    public Environnement build(Element element) {
        if (element == null) {
            return null;
        }
        Element listeElem = element.getChild("liste");
        List<String> liste = new ArrayList<String>();
        for (Element elementElem : (List<Element>) listeElem.getChildren("element")) {
            liste.add(elementElem.getText());
        }
        return new Environnement(liste);
    }
}

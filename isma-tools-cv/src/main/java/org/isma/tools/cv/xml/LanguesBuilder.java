package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Langue;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class LanguesBuilder extends Builder<List<Langue>>{



    public List<Langue> build(Element element) {
        List<Langue> langues = new ArrayList<Langue>();
        for (Object obj : element.getChildren("langue")) {
            langues.add(buildLangues((Element) obj));
        }
        return langues;
    }

    private Langue buildLangues(Element element) {
        return new Langue(
                getRequiredAttributeValue(element, "nom"),
                getRequiredAttributeValue(element, "niveau"));
    }
}

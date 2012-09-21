package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Formation;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class FormationsBuilder extends Builder<List<Formation>> {
    public List<Formation> build(Element element) {
        List<Formation> formations = new ArrayList<Formation>();
        for (Object obj : element.getChildren("formation")) {
            formations.add(buildFormation((Element) obj));
        }
        return formations;
    }

    private Formation buildFormation(Element element) {
        return new Formation(
                getRequiredAttributeValue(element, "nom"),
                getRequiredAttributeValue(element, "annee"),
                getOptionnalAttributeValue(element, "specialisation"));
    }
}

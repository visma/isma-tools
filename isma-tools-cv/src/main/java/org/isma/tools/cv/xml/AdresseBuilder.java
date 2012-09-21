package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Adresse;
import org.jdom.Element;

public class AdresseBuilder extends Builder<Adresse> {
    public Adresse build(Element element) {
        return new Adresse(getRequiredAttributeValue(element, "rue"),
                getRequiredAttributeValue(element, "cp"),
                getRequiredAttributeValue(element, "ville"));
    }
}

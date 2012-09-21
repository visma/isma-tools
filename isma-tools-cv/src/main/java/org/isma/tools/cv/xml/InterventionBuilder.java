package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Intervention;
import org.jdom.Element;

public class InterventionBuilder extends Builder<Intervention> {
    public Intervention build(Element element) {
        if (element == null){
            return null;
        }
        return new Intervention(element.getChild("texte").getText());
    }

}

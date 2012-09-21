package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Mission;
import org.jdom.Element;

public class MissionBuilder extends Builder<Mission> {
    public Mission build(Element element) {
        String poste = getRequiredAttributeValue(element, "poste");
        String duree = getRequiredAttributeValue(element, "duree");
        String dateDebut = getRequiredAttributeValue(element, "dateDebut");
        String dateFin = getRequiredAttributeValue(element, "dateFin");
        String type = getRequiredAttributeValue(element, "type");
        String effectifs = getRequiredAttributeValue(element, "effectifs");
        Element texteElement = element.getChild("texte");
        String texte = texteElement == null ? null : texteElement.getText();
        return new Mission(poste, duree, dateDebut, dateFin, type, effectifs, texte);
    }
}

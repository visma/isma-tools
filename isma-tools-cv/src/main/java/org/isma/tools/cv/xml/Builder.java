package org.isma.tools.cv.xml;

import org.jdom.Element;

public abstract class Builder<B> {
    public abstract B build(Element element);

    protected String getRequiredAttributeValue(Element element, String value) {
        String attributeValue = element.getAttributeValue(value);
        if (attributeValue == null) {
            throw new NullPointerException(String.format("%s non définie sur l'element %s", value, element));
        }
        return attributeValue;
    }
    protected String getOptionnalAttributeValue(Element element, String value) {
        return element.getAttributeValue(value);
    }
}

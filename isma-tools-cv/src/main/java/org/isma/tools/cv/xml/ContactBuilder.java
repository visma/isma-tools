package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Contact;
import org.jdom.Element;

public class ContactBuilder extends Builder<Contact> {

    public Contact build(Element element) {
        return new Contact(
                getRequiredAttributeValue(element, "mail"),
                getRequiredAttributeValue(element, "tel")
        );
    }
}

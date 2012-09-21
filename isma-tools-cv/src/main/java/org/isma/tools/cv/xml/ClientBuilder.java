package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Client;
import org.jdom.Element;

public class ClientBuilder extends Builder<Client> {
    public Client build(Element client) {
        return new Client(
                getRequiredAttributeValue(client, "nom"),
                getRequiredAttributeValue(client, "fonction"),
                getOptionnalAttributeValue(client, "equipe"));
    }
}

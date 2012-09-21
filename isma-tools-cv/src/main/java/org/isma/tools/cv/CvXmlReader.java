package org.isma.tools.cv;

import org.isma.tools.cv.model.Cv;
import org.isma.tools.cv.xml.CvBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.net.URL;

public class CvXmlReader {
    private Element loadXmlDocument(URL resource) throws Exception {
        Document document;
        SAXBuilder sxb = new SAXBuilder();
        document = sxb.build(new File(resource.toURI()));
        return document.getRootElement();
    }

    private Cv toCv(Element element) {
        String tagName = element.getName();
        if (!tagName.equals("cv")){
            throw new IllegalArgumentException(tagName);
        }
        return new CvBuilder().build(element);
    }


    public Cv read(String xmlFile) throws Exception {
        URL resource = CvMain.class.getResource(xmlFile);
        Element element = loadXmlDocument(resource);
        Cv cv = toCv(element);
        //System.out.println(cv);
        return cv;
    }
}

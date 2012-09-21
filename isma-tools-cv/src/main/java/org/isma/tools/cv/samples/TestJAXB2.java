package org.isma.tools.cv.samples;

import org.isma.tools.cv.CvMain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

public class TestJAXB2 {
    public static void main(String[] args) {

        try {
            //JAXBContext jc = JAXBContext.newInstance("org.isma.tools.cv.xjcgenerated");
            JAXBContext jc = JAXBContext.newInstance("org.isma.tools.cv.xmlbeans.generated");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            URL resource = CvMain.class.getResource("cv_MAMECHE.xml");
//            org.isma.tools.cv.xjcmodel.Cv cv = (org.isma.tools.cv.xjcmodel.Cv) unmarshaller.unmarshal(new File(resource.toURI()));
//            org.isma.tools.cv.xjcgenerated.Cv cv = (org.isma.tools.cv.xjcgenerated.Cv) unmarshaller.unmarshal(new File(resource.toURI()));
//            System.out.println(cv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

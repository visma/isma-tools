package org.isma.tools.cv;

import org.apache.commons.io.FileUtils;
import org.isma.tools.cv.model.Cv;
import org.isma.tools.cv.model.Weight;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;

public class CvGenerator {
    private static final String CHARSET = "ISO-8859-1";
    private CvTemplateLoader templateLoader = new CvTemplateLoader();

    public File generateCv(Cv cv, Template template) throws Exception {
        Servlet jspServlet = templateLoader.load(template);

        MockHttpServletRequest request = buildRequest(cv);
        MockHttpServletResponse response = buildResponse();

        jspServlet.service(request, response);

        String content = response.getContentAsString();
        return generateResultFile(template, content, cv.getWeight());
    }

    private File generateResultFile(Template template, String content, Weight weight) throws IOException {
        File templateFile = templateLoader.getTemplateFile(template);
        String outputFileName = template.getFile().replace(".jsp", String.format("_%s.html", weight.name().toLowerCase()));
        File htmlOutputFile = new File(templateFile.getParent(), outputFileName);
        FileUtils.writeStringToFile(htmlOutputFile, content, CHARSET);
        System.out.println("cv généré : " + htmlOutputFile);
        return htmlOutputFile;
    }

    private MockHttpServletResponse buildResponse() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setOutputStreamAccessAllowed(true);
        response.setContentType("text/html; charset=" + CHARSET);
        return response;
    }

    private MockHttpServletRequest buildRequest(Cv cv) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("cv", cv);
        return request;
    }
}

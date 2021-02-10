package com.revature.tier6.answers;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import static org.junit.jupiter.api.Assertions.*;

/**
 * prompt:
 * Register the servlet with tomcat
 * by utilizing the deployment descriptor
 */
@ExtendWith(RevAssess.class)
public class RegisterWithTomcatTests {

    private Node servName, servClass, servlet;

    @BeforeEach
    public void setup() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/main/webapp/WEB-INF/web.xml").getAbsolutePath());
        servClass = doc.getElementsByTagName("servlet-class").item(0);
        servName = doc.getElementsByTagName("servlet-name").item(0);
        servlet = doc.getElementsByTagName("servlet").item(0);

    }

    @RevaTest(tier = 6, points = 100)
    public void testServletInWebXml() {
        //check for nulls to make sure no exceptions are thrown during test execution
        assertNotNull(servlet, "Servlet is not defined inside the web.xml");
        assertNotNull(servClass, "Servlet class is not defined inside the web.xml");
        assertNotNull(servName, "Servlet name is not defined inside the web.xml");

        //test for correct content inside tags
        assertTrue(servClass.getTextContent().equals("com.revature.servlet.RevassessServlet"), "it seems the servlet class defined in the web.xml is not what we expected it to be. Ensure that the class has not moved or been renamed and that there are no typos in the web.xml");
        assertTrue(!servName.getTextContent().isEmpty(), "It seems the Servlet name is empty in the web.xml");


    }
}
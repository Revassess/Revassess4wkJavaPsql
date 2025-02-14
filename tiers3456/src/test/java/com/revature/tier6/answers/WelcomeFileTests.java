package com.revature.tier6.answers;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * prompt: Add the index.html, located inside
 * the src/webapp/html folder, to the web.xml welcome
 * file list.
 */
@ExtendWith(RevAssess.class)
public class WelcomeFileTests {
    private NodeList nl;
    @BeforeEach
    public void setup() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/main/webapp/WEB-INF/web.xml").getAbsolutePath());
        nl = doc.getElementsByTagName("welcome-file");

    }

    @RevaTest(tier = 6, points = 100)
    public void test1() {
        assertTrue(nl.getLength()>0);
        String s;
        for (int i = 0; i<nl.getLength();i++){
            s=nl.item(i).getTextContent();
            System.out.println(s);
            if(s.equals("html/index.html")){
                return;             
            }
        }
        fail("Could not locate the index.html file inside the web.xml welcome file list");
    }
}

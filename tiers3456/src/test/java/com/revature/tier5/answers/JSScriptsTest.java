package com.revature.tier5.answers;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

/**
 * prompt:
 * Use the index HTML file 
 * and include the Bootstrap 
 * CDN link and script tags, 
 * as well as a script tag 
 * that imports the index.js file.
 */
@ExtendWith(RevAssess.class)
public class JSScriptsTest {

    @RevaTest(tier = 5, points = 10)
    public void testScriptTags() {
        try {
            Document html = Jsoup.parse(new File("src/main/webapp/html/index.html"), "UTF-8");
            Elements scripts = html.getElementsByTag("script");
            assertTrue(scripts.size()>=3);
        } catch (Exception e) {
            fail("An exception was thrown during test execution. This could potentially happen if the file was moved or renamed.");
        }
    }

}
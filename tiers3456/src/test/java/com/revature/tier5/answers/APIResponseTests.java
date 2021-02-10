package com.revature.tier5.answers;


import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * prompt: 
 * Successfully consume the data 
 * at the provided endpoint (rendering
 * data to screen not required)
 */
@ExtendWith(RevAssess.class)
public class APIResponseTests {

    private WebDriver wd;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "src/assets/geckodriver.exe");
        wd = new FirefoxDriver();
        File html = Paths.get("src/main/webapp/html/index.html").toFile();
        wd.navigate().to("file://" + html.getAbsolutePath());
        
    }
    @RevaTest(tier = 5, points = 20)
    public void testAjax() {
    	try {
            String s = (String) (new WebDriverWait(wd, 10)).until(ExpectedConditions.jsReturnsValue("return JSON.stringify(getResp())"));
            wd.navigate().to("https://example-for-java-curriculum.s3.amazonaws.com/flashcards.json");
            String json = wd.findElement(By.tagName("body")).getText();
            assertEquals(json.toString(), s, "The object from the resp object in index.js did not match the object gathered from the api.");
    	} catch(Exception e) {
    		fail("Something went wrong. Normally this is an issue with opening firefox web browser or loading the page. \n We will print the message, but note this is a selenium test and your are not expected to understand it fully. Check that everything is correct as expected on your side: \n " + e.getMessage());
    	} finally {
    		wd.close();
    	}
    } 
}
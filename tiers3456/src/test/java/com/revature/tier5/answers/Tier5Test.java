package com.revature.tier5.answers;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        APIResponseTests.class,
        SingleFlashcardTests.class,
        CarouselTests.class,
        JSScriptsTest.class
})
public class Tier5Test {

}
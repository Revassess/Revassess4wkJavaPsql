package com.revature.tier4.answers;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        ConnectionUtilConnectionTests.class,
        ClassStructureTests.class,
        FindByIdTests.class
})
public class Tier4Test {

}
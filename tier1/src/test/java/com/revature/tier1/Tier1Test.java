package com.revature.tier1;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        CompareStrings.class,
        User.class,
        SumOverArrayTests.class,
        ConstructorOverloadingTests.class
})
public class Tier1Test {

}
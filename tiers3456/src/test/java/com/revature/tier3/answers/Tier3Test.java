package com.revature.tier3.answers;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
    ConnectionCredentialsTests.class,
    SequenceTests.class,
    ERDTests.class,
})
public class Tier3Test{
}

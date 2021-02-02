package com.revature.tier1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

/**
 * prompt:
 * Create a method that checks if 
 * two strings are equal and return 
 * the boolean result.
 */
@ExtendWith(RevAssess.class)
public class CompareStringsTests {

    @RevaTest(tier = 1, points = 5)
    public void checkSimpleEquality() {
        assertFalse(CompareStrings.compareStrings("s1", "s2"), "basic implementation of comparing strings does not properly compare strings");
        assertFalse(CompareStrings.compareStrings("jekyll", "hyde"), "basic implementation of comparing strings does not properly compare strings");
        assertFalse(CompareStrings.compareStrings("giraffes", "real"), "basic implementation of comparing strings does not properly compare strings");
        assertTrue(CompareStrings.compareStrings("some string", "some string"), "basic implementation of comparing strings does not properly compare strings");
    }

    @RevaTest(tier=1, points=5)
    public void checkEqualityWithStringConstructor(){
        assertTrue(CompareStrings.compareStrings("gentleman", new String("gentleman")), "Compare Strings method does not properly check for String comparison");
        assertFalse(CompareStrings.compareStrings("great job", new String("great scott!")), "Compare Strings method does not properly check for String comparison");
        assertTrue(CompareStrings.compareStrings("longhorn", new String("longhorn")), "Compare Strings method does not properly check for String comparison");
        assertTrue(CompareStrings.compareStrings("hello", new String("hello")), "Compare Strings method does not properly check for String comparison");
    }

}

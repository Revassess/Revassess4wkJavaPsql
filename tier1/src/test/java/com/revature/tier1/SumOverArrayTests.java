package com.revature.tier1;

import org.junit.jupiter.api.extension.ExtendWith;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * prompt:
 * Create a method that iterates
 * over an integer array and returns
 * their sum.
 */
@ExtendWith(RevAssess.class)
public class SumOverArrayTests {

    @RevaTest(tier = 1, points = 10)
    public void simpeImplTest() {
        int[] arr = {1, 2, 3};
        assertNotEquals(0, SumOverArray.IterateAndSum(arr), "basic implementation test did not pass for sum over array problem.");
    }

    @RevaTest(tier = 1, points = 10)
    public void checkForNullsTest() {
        Integer i = assertDoesNotThrow(() -> SumOverArray.IterateAndSum(null), "Null array caused this test to fail. Make sure you do proper checks for this method.");
    }

    @RevaTest(tier = 1, points = 10)
    public void extraSumsTest() {
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        assertEquals(78, SumOverArray.IterateAndSum(arr2), "Something went wrong with iterating over the array.");
        int[] arr3 = {-1, -2, -3, -4, -5, -6, -7, -8, -9};
        assertEquals(-45, SumOverArray.IterateAndSum(arr3), "Something went wrong with iterating over the array.");
        int[] arr4 = {66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
        assertEquals(1095, SumOverArray.IterateAndSum(arr4), "Something went wrong with iterating over the array.");
    }

}
package com.revature.tier1;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
 * prompt:
 * Create a method that, given an integer, can
 * raise each digit to the power of the overall
 * length and return whether the sum of the powers
 * is equal to the original integer.
 */
@ExtendWith(RevAssess.class)
public class ConstructorOverloadingTests {

    private Class<Person> personClazz = Person.class;



    @RevaTest(tier = 1, points = 8)
    public void checkConstructorCount() {
        int constructorNumber = personClazz.getConstructors().length;
        assertEquals(4, constructorNumber, "We expect 4 constructors in the Person class.");
    }


    @RevaTest(tier = 1, points = 8)
    public void checkAllArgsConstructor() {
        assertTrue(numberOfParamterConstructorExists(3),"The class should have an all args constructor");
        Person p = assertDoesNotThrow(() -> personClazz.getConstructor(String.class, String.class, Integer.TYPE).newInstance("james","mark",1), "Tried to call constructor(String, String, int) but something went wrong.");
        assertEquals(1, p.getAge(), "All args constructor parameters do not match expected result. Ensure parameters are in their proper order.");
        assertEquals("james", p.getFirstname(),"All args constructor parameters do not match expected result. Ensure parameters are in their proper order.");
        assertEquals("mark", p.getLastname(), "All args constructor parameters do not match expected result. Ensure parameters are in their proper order.");
    }

    @RevaTest(tier = 1, points = 8)
    public void checkNoArgsConstructor() {
        assertTrue(numberOfParamterConstructorExists(0), "The class should have a no args constructor");
        Person p = assertDoesNotThrow(() -> personClazz.getConstructor().newInstance(), "Tried to call constructor() but something went wrong.");
        assertEquals(0, p.getAge(), "Two args constructor parameters do not match expected result. Ensure parameters are in their proper order and the default value is given for the corresponding field.");
        assertEquals("John", p.getFirstname(),"Two args constructor parameters do not match expected result. Ensure parameters are in their proper order and default value is given for the corresponding field.");
        assertEquals("Doe", p.getLastname(), "Two args constructor parameters do not match expected result. Ensure parameters are in their proper order and default value is given for the corresponding field.");

    }

    @RevaTest(tier = 1, points = 8)
    public void checkOneArgConstructor() {
        assertTrue(numberOfParamterConstructorExists(1), "The class should have a constructor with a single argument");
        Person p = assertDoesNotThrow(() -> personClazz.getConstructor( Integer.TYPE).newInstance(1),"Tried to call constructor(int) but something went wrong.");
        assertEquals(1, p.getAge(), "One arg constructor parameters do not match expected result. Ensure parameters are in their proper order.");
        assertEquals("John", p.getFirstname(),"One arg constructor parameters do not match expected result. Ensure parameters are in their proper order and the default value is given for the corresponsing field.");
        assertEquals("Doe", p.getLastname(), "One arg constructor parameters do not match expected result. Ensure parameters are in their proper order and default value is given for the corresponding field.");

    }

    @RevaTest(tier = 1, points = 8)
    public void checkTwoArgsConstructor()  {
        assertTrue(numberOfParamterConstructorExists(2), "The class should have a constructor that takes two args");
        Person p = assertDoesNotThrow(() -> personClazz.getConstructor(String.class, Integer.TYPE).newInstance("jones",1), "Tried to call constructor(String, int) but something went wrong.");
        assertEquals(1, p.getAge(), "All args constructor parameters do not match expected result. Ensure parameters are in their proper order.");
        assertEquals("John", p.getFirstname(),"All args constructor parameters do not match expected result. Ensure parameters are in their proper order and the default value is given for the corresponding field.");
        assertEquals("jones", p.getLastname(), "All args constructor parameters do not match expected result. Ensure parameters are in their proper order.");

    }

    private boolean numberOfParamterConstructorExists(int params){
        return Arrays.stream(personClazz.getConstructors()).anyMatch(c -> c.getParameterCount() == params);
    }
}
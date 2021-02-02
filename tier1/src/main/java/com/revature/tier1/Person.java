package com.revature.tier1;

/**
 * This is an immutable class. In it, we have a constructor that provides a way
 * to initialize all fields in the Person class. Your task is to overload the
 * constructor to provide the following:
 *
 * A three (All argument) constructor that initializes all fields
 *
 * A Two argument constructor that provides the following:
 * The default firstname is John
 *
 * A One argument constructor that provides the above and:
 * The default lastname is Doe
 *
 * A no arg constructor that provides the above and:
 * The default age is 0
 *
 *
 * Note: Make sure all constructors are publicly available and parameters are in the same
 * order as the fields are declared in the class.
 */
public class Person {

    private String firstname;
    private String lastname;
    private int age;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //constructors below

}

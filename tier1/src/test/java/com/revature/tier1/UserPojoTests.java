package com.revature.tier1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;


/**
 * prompt:
 * Implement a POJO using the 
 * provided UML class model
 */
@ExtendWith(RevAssess.class)
public class UserPojoTests {

    @RevaTest(tier = 1, points = 20)
    public void fieldsTest() {
        Class<User> userClass;
        List<Field> fields;
        String[] fieldNames = { "id", "firstName", "lastName", "username", "password", "role" };
        userClass = User.class;
        fields = Arrays.asList(userClass.getDeclaredFields());
        assertEquals(fields.size(), fieldNames.length, "Expected number of fields in the User class does not properly meet what is expected.");
        fields.stream().forEach(e -> assertNotEquals(Arrays.asList(fieldNames).indexOf(e.getName()), -1, "Field names do not match for the User Class."));
    }

}
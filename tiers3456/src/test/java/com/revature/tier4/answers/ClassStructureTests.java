package com.revature.tier4.answers;

import com.revature.dao.FlashcardRepository;
import com.revature.dao.UserRepository;
import com.revature.exception.ResourceNotfoundException;
import com.revature.exception.ResourcePersistenceException;
import com.revature.model.Flashcard;
import com.revature.model.User;
import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * prompt:
 * Implement only the CrudRepository 
 * interface methods within UserRepository 
 * class using the provided class diagram
 * 
 */
@ExtendWith(RevAssess.class)
public class ClassStructureTests {

    @RevaTest(tier = 4, points = 10)
    public void checkInheritance(){
        try {
            Class<?>[] userRepoInterfaces = UserRepository.class.getInterfaces();
            assertTrue(asList(userRepoInterfaces).contains(com.revature.dao.CrudRepository.class));
            Class<?>[] flashRepoInterfaces = FlashcardRepository.class.getInterfaces();
            assertTrue(asList(flashRepoInterfaces).contains(com.revature.dao.CrudRepository.class));
            assertTrue(Throwable.class.isAssignableFrom(ResourceNotfoundException.class));
            assertTrue(Throwable.class.isAssignableFrom(ResourcePersistenceException.class));

        } catch(Exception e) {
            fail("An exception was thrown while checking the class structures: " + e.getMessage());
        }
    }

    @RevaTest(tier = 4, points = 10)
    public void fieldTest(){
        String[] userFields = {"id","firstName","lastName","username","password","role"};
        String[] flashcardFields = {"id","question","answer","category"};
        List<Field> actualUserFields = Arrays.asList(User.class.getFields());
        List<Field> actualFlashcardFields = Arrays.asList(Flashcard.class.getFields());
        actualUserFields.stream().forEach(e -> assertNotEquals(Arrays.asList(userFields).indexOf(e.getName()), -1, "Field from the User class do not match the expected names."));
        actualFlashcardFields.stream().forEach(e -> assertNotEquals(Arrays.asList(flashcardFields).indexOf(e.getName()),-1, "Fields from the flashcard class do not match the expected names."));
    }
}
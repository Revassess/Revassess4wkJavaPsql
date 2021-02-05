package com.revature.tier2;

import static com.revature.tier2.config.TestConfiguration.getSQLFileContents;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;


import com.revature.tier2.config.TestConfiguration;
import com.revature.tier2.model.Flashcard;
import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * prompt: Write a query that will obtain the owner’s username, as well as the
 * category name, questions, and answers of flashcard contained within the study
 * set with and id of 4.
 */
@ExtendWith(RevAssess.class)
public class Answer4Tests {

    private String answer4Contents;

    @BeforeEach
    public void setup() {
        try {
            answer4Contents = getSQLFileContents("answer4");
        } catch (IOException e) {
            System.err.println("Could not find file: src/sql/answer4.sql");
        }
    }

    @RevaTest(tier = 2, points = 40)
    public void JoinTest() {
        Session sess = TestConfiguration.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        if (answer4Contents == null) {
            fail("File Not Found: src/sql/answer4.sql");
        }
        try {
            List<Flashcard> questions = sess.createNativeQuery(answer4Contents, Flashcard.class).list();

            assertEquals(12, questions.size(), "The query did not provide the expected result.");
            questions.forEach(flashcard -> assertTrue(flashcard.getQuestion().matches(".*Question 1.*"),"The questions do not meet the format"));
        } finally {

            tx.rollback();

        }
    }

}
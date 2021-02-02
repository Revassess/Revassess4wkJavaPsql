package com.revature.tier2;

import static com.revature.tier2.config.TestConfiguration.getSQLFileContents;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;


import com.revature.tier2.config.TestConfiguration;
import com.revature.tier2.model.UserProblem4;
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
            e.printStackTrace();
        }
    }

    @RevaTest(tier = 2, points = 40)
    public void JoinTest() {
        Session sess = TestConfiguration.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        try {
            List<UserProblem4> users = sess.createNativeQuery(answer4Contents, UserProblem4.class).list();
            assertEquals(9, users.size());
        } finally {
            tx.rollback();

        }
    }

}
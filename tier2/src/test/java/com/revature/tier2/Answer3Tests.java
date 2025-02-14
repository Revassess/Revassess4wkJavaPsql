package com.revature.tier2;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import com.revature.tier2.config.TestConfiguration;
import com.revature.tier2.model.UserStudySet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

import static com.revature.tier2.config.TestConfiguration.getSQLFileContents;
import static org.junit.jupiter.api.Assertions.*;

/**
 * prompt: Write a query that will find all user and study set info related to
 * the user with an id of 5
 */
@ExtendWith(RevAssess.class)
public class Answer3Tests {

    private String answer3Contents;

    @BeforeEach
    public void setup() {
        try {
            answer3Contents = getSQLFileContents("answer3");
        } catch (IOException e) {
            System.err.println("Could not find file: answer3.sql");
        }
    }

    @RevaTest(tier = 2, points = 30)
    public void userAndStudySetTest() {
        Session sess = TestConfiguration.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        if(answer3Contents == null) {
            fail("File Not Found: src/sql/answer3.sql");
        }
        try {
            List<UserStudySet> users = sess.createNativeQuery(answer3Contents, UserStudySet.class).list();
            assertEquals(3, users.size());
            assertEquals(4,users.get(0).getRole().getRole_id());
        } finally {
            tx.rollback();
        }
    }
}

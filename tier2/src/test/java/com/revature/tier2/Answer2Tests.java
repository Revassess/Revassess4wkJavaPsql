package com.revature.tier2;

import com.revature.tier2.config.TestConfiguration;
import com.revature.tier2.model.User;
import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.revature.tier2.config.TestConfiguration.getSQLFileContents;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * prompt: Write a statement that will insert a new user into the APP_user table
 * with a role of PREMIUM_user
 */
@ExtendWith(RevAssess.class)
public class Answer2Tests {

    private String answer2Contents;

    @BeforeEach
    public void setup() {
        try {
            answer2Contents = getSQLFileContents("answer2");
        } catch (IOException e) {
            System.err.println("Could not find file: src/sql/answer2.sql");
        }
    }

    @RevaTest(tier = 2, points = 20)
    public void insertPremUserTest() {
        Session sess = TestConfiguration.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        List<User> after, before;
        if(answer2Contents == null){
            fail("File Not Found: src/sql/answer2.sql");
        }
        try {
            before = sess.createQuery("from User where roleId=4", User.class).list();
            sess.createNativeQuery(answer2Contents, User.class).executeUpdate();
            after = sess.createQuery("from User where roleId=4", User.class).list();
            assertEquals(after.size(), before.size() + 1);
        } finally {
            tx.rollback();
        }
    }
}
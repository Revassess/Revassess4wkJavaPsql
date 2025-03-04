package com.revature.tier3.answers;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import com.revature.config.TestConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * prompt: Create a table structure using the provided ERD, ensure that the
 * ability for auto-incrementing IDs
 */
@ExtendWith(RevAssess.class)
public class ERDTests {

    @RevaTest(tier = 3, points = 30)
    public void erdTest() {
        Session sess = TestConfig.getInstance().openSession();
        Transaction tx = sess.beginTransaction();
        try{
            assertNotNull(sess.createNativeQuery(
                "insert into app_user (username, password, first_name, last_name, role_id) values ('testing','testing','testing','testing',1"));
        assertNotNull(sess.createNativeQuery(
                "insert into flashcard (question, answer, category_id) values ('testing','testing',2)"));
        assertNotNull(sess.createNativeQuery("insert into study_set (name, owner_id) values ('testing',2)"));
        } catch(Exception e){
            fail();
        } finally {
            tx.rollback();
        }
    }

}
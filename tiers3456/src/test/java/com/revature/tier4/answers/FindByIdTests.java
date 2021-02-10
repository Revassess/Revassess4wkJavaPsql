package com.revature.tier4.answers;

import com.revature.config.ConnectionUtil;
import com.revature.dao.CrudRepository;
import com.revature.dao.UserRepository;
import com.revature.model.User;
import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;
import org.hibernate.Filter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(RevAssess.class)
public class FindByIdTests {

    Class<?> shouldBeCrudRepository;
    CrudRepository repo;

    @BeforeEach
    public void setup(){
        repo = null;
        Class<?>[] interfaces = UserRepository.class.getInterfaces();
        shouldBeCrudRepository = interfaces.length >0? interfaces[0] : null;
    }

    @RevaTest(tier = 4, points = 30)
    public void TestFindByIdMethodForUserRepo() throws SQLException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if (shouldBeCrudRepository != null && shouldBeCrudRepository.equals(CrudRepository.class)) {
            repo = (CrudRepository) new UserRepository();
        } else {
            fail("UserRepository does not meet the inheritance requirements");
        }

        //Create a new configuration for this test. ERD's should be the same to we can
        //  connect this test to the revassess_test db to test method implementation.
        ConnectionUtil cu = Mockito.mock(ConnectionUtil.class);
        Field instance = ConnectionUtil.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, cu);
        Mockito.when(cu.connect()).thenReturn(DriverManager.getConnection("jdbc:postgresql://postgresql-class.cks98gmxels6.us-west-1.rds.amazonaws.com:5432/revassess_test", "tester", "password"));
        User u = (User)repo.getClass().getDeclaredMethod("findById", Integer.TYPE).invoke(repo, 1);
        assertEquals(u.getClass().getDeclaredMethod("getUsername").invoke(u), "wsingleton", "username is not as expected");
    }


}

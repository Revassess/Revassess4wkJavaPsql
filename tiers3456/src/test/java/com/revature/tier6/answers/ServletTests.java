package com.revature.tier6.answers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.servlet.RevassessServlet;


/**
 * prompt: Implement a single servlet 
 * that can provide flashcard data by 
 * consuming its mapped endpoint. The 
 * data should be in json format and 
 * information should come from the 
 * database using the entities created 
 * in tier 4.
 */
@ExtendWith(RevAssess.class)
public class ServletTests {

    private HttpServlet serv;
    private Set<String> jsonKeys;
    private Method doGet;


    @BeforeEach
    public void setup() throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        RevassessServlet rev = new RevassessServlet();

        //Use reflection to check if RevassessServlet extends http servlet
        if(HttpServlet.class.isInstance(rev)){

            //use reflection to provide an instance of RevassessServlet to the http servlet instance in this class
            Field thisServ = ServletTests.class.getDeclaredField("serv");
            thisServ.setAccessible(true);
            thisServ.set(this, rev);
        } else {

            //if revassess servlet is not compatible with http servlet, then provide a default impl to get the tests to run
            //      The tests will fail if this block is ran, it is important to not let the test process stop here.
            serv = new HttpServlet() {
                private static final long serialVersionUID = 1L;
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                }
            };
        }

        // now we can give the Method object 'doGet' belonging to this class the implementation belonging to the serv obj.
        doGet = serv.getClass().getSuperclass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        doGet.setAccessible(true);
    }

    // create a set to compare with the mapped json in the test
    @BeforeEach
    public void setupJson(){
        jsonKeys = new HashSet<>();
        jsonKeys.add("question");
        jsonKeys.add("answer");
        jsonKeys.add("id");
        jsonKeys.add("category");
    }

    @RevaTest(tier = 6, points = 100)
    public void test2() {
    	try {

    	    //Mocking requests, reponses and setting up other objects to work with this test.
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        doGet.invoke(serv, request, response);
        writer.flush();

        //get the json returned from the method and turn it into a map
        ObjectMapper om = new ObjectMapper();
        String output = stringWriter.toString();
        TypeReference<Map<String, Object>> t = new TypeReference<Map<String,Object>>() {};
        Map<String, Object> servletJson = (Map<String, Object>) om.readValue(output, t);

        //compare the keys in the returned json with the keys in the existing set of expected json keys
        jsonKeys.stream().forEach(s->assertTrue(servletJson.containsKey(s)));
    	} catch(Exception e) {
    		fail("An exception was thrown during test execution. This can be caused by RevassessServlet class not being properly setup");
    	}
    }

    

    
}

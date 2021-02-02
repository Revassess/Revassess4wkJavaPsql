package com.revature.tier2.config;

import com.revature.tier2.model.User;
import com.revature.tier2.model.UserProblem4;
import com.revature.tier2.model.UserStudySet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class TestConfiguration {
    private static SessionFactory sesfact;
    static String engine;

    private static SessionFactory buildFactory() throws IOException {
        return new Configuration()
        		.setProperty("hibernate.connection.url","jdbc:postgresql://postgresql-class.cks98gmxels6.us-west-1.rds.amazonaws.com:5432/revassess_test")
        		.setProperty("hibernate.connection.username", "tester")
        		.setProperty("hibernate.connection.password", "password")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.pool_size", "1")
                .setProperty("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_SERIALIZABLE))
                .setProperty("hibernate.hbm2ddl.auto", "none").setProperty("hibernate.show_sql", "true")
                .addAnnotatedClass(UserStudySet.class)
                .addAnnotatedClass(UserProblem4.class)
                .addAnnotatedClass(User.class).buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sesfact == null) {
            try {
                sesfact = buildFactory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sesfact;
    }

    @Deprecated
    private static String findDriver(Properties props) {
        engine = props.getProperty("database.engine");
        switch (engine) {
            case "oraclesql":
                return "oracle.jdbc.OracleDriver";
            case "postgresql":
                return "org.postgresql.Driver";
            case "mysql":
                return "com.mysql.jdbc.Driver";
            default:
                return "";
        }
    }

    public static String getEngine() {
        return engine;
    }

    public static String getSQLFileContents(String filename) throws IOException {
        File answer = new File("src/sql/" + filename + ".sql");
        String contents = "";

        String line;
        BufferedReader br = new BufferedReader(new FileReader(answer));
        while ((line = br.readLine()) != null) {
            contents += (" " + line);
        }
        br.close();
        return contents.replace(';',' ');
    }
}
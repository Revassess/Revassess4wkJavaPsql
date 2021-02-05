package com.revature.tier2.config;

import com.revature.tier2.model.Category;
import com.revature.tier2.model.User;
import com.revature.tier2.model.Flashcard;
import com.revature.tier2.model.UserStudySet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestConfiguration {
    private static SessionFactory sesfact;

    // field for the findDriver method. Deprecated now that configuration has moved completely to this class
    //     but may use it again in the future.
    @Deprecated
    static String engine;

    private static SessionFactory buildFactory() throws IOException {

        // This will turn off logging for all loggers. Done mainly for hibernate logging to keep the
        // //   console clear for associates. Still logs assertion and sql exceptions
        Enumeration<String> loggers = LogManager.getLogManager().getLoggerNames();
        while (loggers.hasMoreElements()) {
            Optional.ofNullable(Logger.getLogger(loggers.nextElement())).ifPresent(logger -> logger.setLevel(Level.SEVERE));
        }

        //Configure hibernate. Formerly stored in a properties file, now just in a configuration object.
        return new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:postgresql://postgresql-class.cks98gmxels6.us-west-1.rds.amazonaws.com:5432/revassess_test")
                .setProperty("hibernate.connection.username", "tester")
                .setProperty("hibernate.connection.password", "password")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                //hibernate pool size should remain at 1 so it doesn't overload the db with connections. If
                // //   using local db, this can be changed to speed up testing.
                .setProperty("hibernate.connection.pool_size", "1")
                // ensure multiple associates are in no way interacting with each others transactions in tests.
                .setProperty("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_SERIALIZABLE))
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .setProperty("hibernate.show_sql", "false")
                .addAnnotatedClass(UserStudySet.class)
                .addAnnotatedClass(Flashcard.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();
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

    // deprecated due to configuration moving directly in the class and not really needing it any more. May use it
    //  in the future, but not needed right now.
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
        return contents.replace(';', ' ');
    }
}
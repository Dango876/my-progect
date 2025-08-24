package jm.task.core.jdbc.util;

import java.util.Properties;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private SessionFactory sessionFactory = null;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.setProperty("hibernate.connection.url", URL);
                settings.setProperty("hibernate.connection.username", USER);
                settings.setProperty("hibernate.connection.password", PASSWORD);
                settings.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                settings.setProperty("hibernate.hbm2ddl.auto", "update");
                sessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(settings)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}

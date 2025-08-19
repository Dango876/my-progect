package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/world";
    private static final String user = "root";
    private static final String password = "root";

    private SessionFactory sessionFactory = null;

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException s) {
            System.err.println("Не удалось загрузить класс драйвера БД");
        }
        return con;
    }

    public Session getSession() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/world");
                settings.setProperty("hibernate.connection.username", "root");
                settings.setProperty("hibernate.connection.password", "root");
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
        return sessionFactory.openSession();
    }
}

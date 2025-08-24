package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());

    private final SessionFactory sessionFactory = new Util().getSessionFactory();

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS world (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS world";
    private static final String TRUNCATE_TABLE_SQL = "TRUNCATE TABLE world";

    @Override
    public void createUsersTable() {
        logger.log(Level.FINE, "Creating a user table");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();
            logger.log(Level.INFO, "The user table has been created successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating user table", e);
        }
    }

    @Override
    public void dropUsersTable() {
        logger.log(Level.FINE, "Deleting the user table");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(DROP_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();
            logger.log(Level.INFO, "The user table has been deleted successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting the user table", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        logger.log(Level.FINE, "Saving user: name={0}, lastName={1}, age={2}",
                new Object[]{name, lastName, age});
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.merge(user);
            transaction.commit();
            logger.log(Level.INFO, "User {0} added to the table", name);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                logger.log(Level.WARNING, "Operation rejected while saving user: {0}", name);
            }
            logger.log(Level.SEVERE, "Error saving user: " + name, e);
        }
    }

    @Override
    public void removeUserById(long id) {
        logger.log(Level.FINE, "Deleting user with ID: {0}", id);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
                logger.log(Level.INFO, "User with id {0} successfully deleted", id);
            } else {
                logger.log(Level.WARNING, "User with id {0} not found", id);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)  {
                transaction.rollback();
                logger.log(Level.WARNING, "Error saving user with id: {0}", id);
            }
            logger.log(Level.SEVERE, "Error deleting user with ID: " + id, e);

        }
    }

    @Override
    public List<User> getAllUsers() {
        logger.log(Level.FINE, "Get all user");
        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            logger.log(Level.INFO, "Received {0} users from the database", users.size());
            return users;
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "Error getting list of users", e);
            return List.of();
        }
    }

    @Override
    public void cleanUsersTable() {
        logger.log(Level.FINE, "Clearing the user table");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(TRUNCATE_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();
            logger.log(Level.INFO, "The user table has been cleared");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error clearing user table", e);
        }
    }
}

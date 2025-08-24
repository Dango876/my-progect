package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        logger.log(Level.INFO, "Creating a user table via the service");
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        logger.log(Level.INFO, "Deleting the user table via the service");
        userDaoHibernate.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        logger.log(Level.INFO, "Saving a user via the service: {0}", name);
        userDaoHibernate.saveUser(name, lastName, age);
        logger.log(Level.INFO, "User with user – {0} added to database", name);
    }

    @Override
    public void removeUserById(long id) {
        logger.log(Level.INFO, "Deleting a user by ID via the service: {0}", id);
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        logger.log(Level.INFO, "Getting all users through services");
        List<User> users = userDaoHibernate.getAllUsers();
        if (users.isEmpty()) {
            logger.log(Level.INFO, "There are no users in the database");
        } else {
            logger.log(Level.INFO, "Found {0} user:", users.size());
            for (User user : users) {
                logger.log(Level.INFO, user.toString());
            }
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        logger.log(Level.INFO, "Clearing the user table via service");
        userDaoHibernate.cleanUsersTable();
    }
}

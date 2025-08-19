package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();

        userService.saveUser("Александр", "Иванов", (byte) 30);
        userService.saveUser("Мария", "Смирнова", (byte) 24);
        userService.saveUser("Дмитрий", "Петров", (byte) 43);
        userService.saveUser("Ольга", "Кузнецова", (byte) 27);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

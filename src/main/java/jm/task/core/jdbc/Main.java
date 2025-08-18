package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Александр", "Иванов", (byte) 90);
        userService.saveUser("Мария", "Смирнова", (byte) 80);
        userService.saveUser("Дмитрий", "Петров", (byte) 70);
        userService.saveUser("Ольга", "Кузнецова", (byte) 60);

        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tom", "Shelby", (byte) 40);
        userService.saveUser("Bob", "Stone", (byte) 80);
        userService.saveUser("Ben", "Ten", (byte) 24);
        userService.saveUser("Tim", "Bug", (byte) 50);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

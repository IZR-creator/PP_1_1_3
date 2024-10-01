package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE user (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, lastname VARCHAR(100), age TINYINT)";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS user";
    private static final String SAVE_USER = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM user WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM user";
    private static final String CLEAN_USERS_TABLE = "TRUNCATE TABLE user";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No createUsersTable");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(DROP_USERS_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No dropUsersTable");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No saveUser");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No removeUserById");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("No getAllUsers");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USERS_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("No cleanUsersTable");
        }
    }
}

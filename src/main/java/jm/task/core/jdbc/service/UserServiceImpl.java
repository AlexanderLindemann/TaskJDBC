package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private Session session;
    private SessionFactory sessionFactory;

    public void createUsersTable() {
        try {
            UserDaoJDBCImpl.getInstance().createUsersTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl.getInstance().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl.getInstance().saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl.getInstance().removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> list = UserDaoJDBCImpl.getInstance().getAllUsers();
        return list;
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl.getInstance().cleanUsersTable();
    }
}

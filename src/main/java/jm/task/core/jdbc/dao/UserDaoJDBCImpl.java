package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Session session;

    public static class UserDaoJDBCImplHolder {
        public static final UserDaoJDBCImpl HOLDER_INSTANCE = new UserDaoJDBCImpl();
    }

    public static UserDaoJDBCImpl getInstance() {
        return UserDaoJDBCImplHolder.HOLDER_INSTANCE;
    }


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE users (\n" +
                "            id INT NOT NULL auto_increment,\n" +
                "            name VARCHAR(50) default NULL,\n" +
                "    lastName VARCHAR(50) default NULL,\n" +
                "    age INT default NULL,\n" +
                "   );");
        transaction.commit();
        session.close();

    }

    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("delete from db_example.users").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        List<User> list = Util.getSessionFactory().openSession().createQuery("from User").list();
        return list;
    }

    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        transaction.commit();
        session.close();
    }
}

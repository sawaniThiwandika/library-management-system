package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;

public interface UserDao {
    public boolean save(Session session,User user);
}

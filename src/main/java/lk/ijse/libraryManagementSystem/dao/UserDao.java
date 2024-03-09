package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.Branch;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDao {
    public boolean save(Session session,User user);
    public List<User> getAll(Session session);
    boolean update(Session session, User user);
    User search(Session session, String email);
}

package lk.ijse.libraryManagementSystem.dao.custom;

import lk.ijse.libraryManagementSystem.dao.SuperDao;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.Branch;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDao extends SuperDao {
    public boolean save(User user);
    public List<User> getAll();
    boolean update( User user);
    User search( String email);
    boolean delete(User user);
}

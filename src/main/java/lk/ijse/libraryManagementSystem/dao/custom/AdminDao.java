package lk.ijse.libraryManagementSystem.dao.custom;

import lk.ijse.libraryManagementSystem.dao.SuperDao;
import lk.ijse.libraryManagementSystem.entity.Admin;
import org.hibernate.Session;

import java.util.List;

public interface AdminDao extends SuperDao {
    public List<Admin> getAll();
}

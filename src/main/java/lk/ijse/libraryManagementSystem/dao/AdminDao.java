package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.Admin;
import org.hibernate.Session;

import java.util.List;

public interface AdminDao {
    public List<Admin> getAll();
}

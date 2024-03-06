package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.AdminDao;
import lk.ijse.libraryManagementSystem.entity.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public List<Admin> getAll(Session session) {
        String hql=" FROM Admin ";
        Query<Admin> query = session.createQuery(hql,Admin.class);
        return query.list();
    }
}

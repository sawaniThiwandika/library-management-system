package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.UserDao;
import lk.ijse.libraryManagementSystem.entity.Branch;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(Session session,User user) {
        Transaction transaction = session.beginTransaction();
        String saved = (String) session.save(user);
        transaction.commit();
        if (saved==null){
            System.out.println("result if"+saved);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<User> getAll(Session session) {
        String hql=" FROM User ";
        Query<User> query = session.createQuery(hql, User.class);
        return query.list();
    }

    @Override
    public boolean update(Session session, User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        return true;
    }
}

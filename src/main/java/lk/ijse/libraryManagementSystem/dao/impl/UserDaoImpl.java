package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.UserDao;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}

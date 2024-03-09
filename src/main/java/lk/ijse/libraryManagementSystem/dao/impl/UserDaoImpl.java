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
        System.out.println("contact"+user.getContact());
        System.out.println("pwd"+user.getPassword());
        System.out.println("name"+user.getName());
        System.out.println("email"+user.getEmail());
        Transaction transaction = session.beginTransaction();
        //session.update(user);
      String hql="UPDATE User SET contact=:newContact, password=:newPassword,name=:newName WHERE email=:newEmail";
        Query query = session.createQuery(hql);
        System.out.println("contact"+user.getContact());
        System.out.println("pwd"+user.getPassword());
        System.out.println("name"+user.getName());
        System.out.println("email"+user.getEmail());
        query.setParameter("newContact",user.getContact());
        query.setParameter("newPassword",user.getPassword());
        query.setParameter("newName",user.getName());
        query.setParameter("newEmail",user.getEmail());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        return true;
    }

    @Override
    public User search(Session session, String email) {
       User user = (User) session.get(User.class,email );
       return user;

    }
}

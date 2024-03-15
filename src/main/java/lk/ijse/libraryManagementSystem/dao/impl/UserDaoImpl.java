package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.UserDao;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        /*if (saved == null) {
            System.out.println("result if" + saved);
            return false;
        } else {
            return true;
        }*/
        return true;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM User ";
        Query<User> query = session.createQuery(hql, User.class);
        List<User> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean update(User user) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        System.out.println("contact" + user.getContact());
        System.out.println("pwd" + user.getPassword());
        System.out.println("name" + user.getName());
        System.out.println("email" + user.getEmail());
        Transaction transaction = session.beginTransaction();
        //session.update(user);
        String hql = "UPDATE User SET contact=:newContact, password=:newPassword,name=:newName WHERE email=:newEmail";
        Query query = session.createQuery(hql);
        System.out.println("contact" + user.getContact());
        System.out.println("pwd" + user.getPassword());
        System.out.println("name"+user.getName());
        System.out.println("email"+user.getEmail());
        query.setParameter("newContact",user.getContact());
        query.setParameter("newPassword",user.getPassword());
        query.setParameter("newName",user.getName());
        query.setParameter("newEmail",user.getEmail());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String email) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        User user = session.find(User.class, email);
        session.close();
        return user;

    }

    @Override
    public boolean delete(User user) {

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
       /* String hql = "UPDATE Book SET isAvailable=:status WHERE id=:id";
        for (int i=0; i<user.getTransactions().size();i++){
            Query query = session.createQuery(hql);
            query.setParameter("status", false);
            query.setParameter("id", user.getTransactions().get(i).getBook().getId());
            boolean b = query.executeUpdate() > 0;
        }
*/

        session.remove(user);
        transaction.commit();
        session.close();
        return true;
    }
}

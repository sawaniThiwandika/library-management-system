package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.TransactionDao;
import lk.ijse.libraryManagementSystem.entity.UserBookDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public UserBookDetails generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM UserBookDetails ORDER BY id DESC LIMIT 1";

        Query<UserBookDetails> query = session.createQuery(hql, UserBookDetails.class);
        List<UserBookDetails> list = query.list();
        session.close();
        if (list.isEmpty()) {
            return new UserBookDetails();
        } else {
            return list.get(0);
        }
    }

    @Override
    public boolean save(UserBookDetails borrow) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String result = (String) session.save(borrow);
        String hql = "UPDATE Book SET isAvailable=:status WHERE id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("status", false);
        query.setParameter("id", borrow.getBook().getId());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        System.out.println("result" + result);
        if (result==null || !b){
            System.out.println("result if"+result);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<UserBookDetails> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM UserBookDetails";
        Query<UserBookDetails> query = session.createQuery(hql, UserBookDetails.class);
        List<UserBookDetails> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean update(UserBookDetails userBookDetails) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(userBookDetails);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean returnBook(UserBookDetails userBookDetails) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(userBookDetails);
        String hql = "UPDATE Book SET isAvailable=:status WHERE id=:id";
        System.out.println("book id" + userBookDetails.getBook().getId());

        Query query = session.createQuery(hql);
        query.setParameter("status", true);
        query.setParameter("id", userBookDetails.getBook().getId());
        boolean b = query.executeUpdate() > 0;
        System.out.println("b" + b);
        transaction.commit();
        session.close();
        return b;
    }
}

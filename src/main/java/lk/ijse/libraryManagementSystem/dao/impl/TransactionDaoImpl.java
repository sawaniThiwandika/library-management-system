package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.TransactionDao;
import lk.ijse.libraryManagementSystem.entity.Branch;
import lk.ijse.libraryManagementSystem.entity.UserBookDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public UserBookDetails generateNewId(Session session) {
        String hql=" FROM UserBookDetails ORDER BY id DESC LIMIT 1";

        Query<UserBookDetails> query = session.createQuery(hql, UserBookDetails.class);
        if(query.list().isEmpty()){
            return new UserBookDetails();
        }
        else{return query.list().get(0);}
    }

    @Override
    public boolean save(Session session, UserBookDetails borrow) {
        Transaction transaction = session.beginTransaction();
        String result = (String) session.save(borrow);
        String hql="UPDATE Book SET isAvailable=:status WHERE id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("status",false);
        query.setParameter("id",borrow.getBook().getId());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        System.out.println("result"+result);
        if (result==null || !b){
            System.out.println("result if"+result);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<UserBookDetails> getAll(Session session) {
        String hql=" FROM UserBookDetails";
        Query<UserBookDetails> query = session.createQuery(hql, UserBookDetails.class);
        return query.list();
    }

    @Override
    public boolean update(Session session, UserBookDetails userBookDetails) {
        Transaction transaction = session.beginTransaction();
        session.update(userBookDetails);
        transaction.commit();
        return true;
    }
}

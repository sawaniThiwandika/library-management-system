package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.BookDao;
import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public Book generateNewId(Session session) {
        String hql="FROM Book ORDER BY id DESC LIMIT 1";
        Query<Book> query = session.createQuery(hql, Book.class);
        if (query.list().isEmpty()){
            return new Book();
        }
        else {
            return query.list().get(0);
        }


    }

    @Override
    public boolean save(Session session, Book book) {
        Transaction transaction = session.beginTransaction();
        String saved = (String) session.save(book);
        transaction.commit();
        if (saved==null){
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public List<Book> getAll(Session session) {
        String hql=" FROM Book ";
        Query<Book> query = session.createQuery(hql, Book.class);
        return query.list();
    }
}

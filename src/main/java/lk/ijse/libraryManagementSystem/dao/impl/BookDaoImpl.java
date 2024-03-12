package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
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

    @Override
    public boolean update(Session session, Book book) {
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        return true;

    }

    @Override
    public Book getBook(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = (Book) session.get( Book.class,id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public boolean delete(Book book) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
       /* String hql="DELETE Branch WHERE id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",branch.getId());*/
        session.delete(book);
        transaction.commit();
        session.close();
        return true;
    }
}

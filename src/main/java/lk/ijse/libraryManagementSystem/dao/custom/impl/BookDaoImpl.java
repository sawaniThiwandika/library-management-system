package lk.ijse.libraryManagementSystem.dao.custom.impl;

import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.custom.BookDao;
import lk.ijse.libraryManagementSystem.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public Book generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();


        String hql = "FROM Book ORDER BY id DESC LIMIT 1";
        Query<Book> query = session.createQuery(hql, Book.class);
        List<Book> list = query.list();
        session.close();
        if (list.isEmpty()) {
            return new Book();
        } else {
            return list.get(0);
        }


    }

    @Override
    public boolean save(Book book) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String saved = (String) session.save(book);
        transaction.commit();
        session.close();
        if (saved == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Book> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM Book ";
        Query<Book> query = session.createQuery(hql, Book.class);
        List<Book> list = query.list();
        session.close();
        return list;

    }

    @Override
    public boolean update(Book book) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
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

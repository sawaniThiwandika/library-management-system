package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public interface BookDao {
    public Book generateNewId(Session session);
    public boolean save(Session session, Book book);
    public List<Book> getAll(Session session);

    boolean update(Session session, Book book);

}

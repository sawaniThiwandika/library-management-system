package lk.ijse.libraryManagementSystem.dao.custom;

import lk.ijse.libraryManagementSystem.dao.SuperDao;
import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public interface BookDao extends SuperDao {
    public Book generateNewId();
    public boolean save( Book book);
    public List<Book> getAll();

    boolean update( Book book);

    Book getBook(String id);
    boolean delete(Book book);
}

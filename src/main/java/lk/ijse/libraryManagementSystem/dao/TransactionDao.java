package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.UserBookDetails;
import org.hibernate.Session;

import java.util.List;

public interface TransactionDao {
    public UserBookDetails generateNewId();
    public boolean save( UserBookDetails transaction);
    public List<UserBookDetails> getAll();
    public boolean update( UserBookDetails transaction);
    public boolean returnBook( UserBookDetails transaction);
    public  List<UserBookDetails> lateReturns();

}

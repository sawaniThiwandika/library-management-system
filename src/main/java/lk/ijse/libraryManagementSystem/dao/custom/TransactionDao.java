package lk.ijse.libraryManagementSystem.dao.custom;

import lk.ijse.libraryManagementSystem.dao.SuperDao;
import lk.ijse.libraryManagementSystem.entity.UserBookDetails;
import org.hibernate.Session;

import java.util.List;

public interface TransactionDao extends SuperDao {
    public UserBookDetails generateNewId();
    public boolean save( UserBookDetails transaction);
    public List<UserBookDetails> getAll();
    public boolean update( UserBookDetails transaction);
    public boolean returnBook( UserBookDetails transaction);
    public  List<UserBookDetails> lateReturns();

}

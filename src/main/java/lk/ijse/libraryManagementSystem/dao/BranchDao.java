package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public interface BranchDao {
   public Branch generateNewId(Session session);
   public boolean save(Session session, Branch branch);
   public List<Branch> getAll(Session session);
   boolean update(Session session, Branch branch);
}

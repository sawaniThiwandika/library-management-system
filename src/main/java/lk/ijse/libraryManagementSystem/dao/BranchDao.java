package lk.ijse.libraryManagementSystem.dao;

import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public interface BranchDao extends SuperDao {
   public Branch generateNewId();
   public boolean save( Branch branch);
   public List<Branch> getAll();
   boolean update(Branch branch);

   boolean delete(Branch id);
   Branch getBranch(String id);

}

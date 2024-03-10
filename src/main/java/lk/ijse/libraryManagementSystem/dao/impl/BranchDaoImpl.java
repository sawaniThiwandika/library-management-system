package lk.ijse.libraryManagementSystem.dao.impl;

import lk.ijse.libraryManagementSystem.dao.BranchDao;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDaoImpl implements BranchDao {

    @Override
    public Branch generateNewId(Session session) {
        String hql=" FROM Branch ORDER BY id DESC LIMIT 1";
        Query<Branch> query = session.createQuery(hql, Branch.class);
        if(query.list().isEmpty()){
            return new Branch();
        }
        else{return query.list().get(0);}

    }

    @Override
    public boolean save(Session session, Branch branch) {
        Transaction transaction = session.beginTransaction();
        String result = (String) session.save(branch);
        transaction.commit();
        System.out.println("result"+result);
        if (result==null){
            System.out.println("result if"+result);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<Branch> getAll(Session session) {
        String hql=" FROM Branch ";
        Query<Branch> query = session.createQuery(hql, Branch.class);
        return query.list();
    }

    @Override
    public boolean update(Session session, Branch branch) {
        Transaction transaction = session.beginTransaction();
        String hql="UPDATE Branch SET name=:newName, contact=:newContact, email=: newEmail,address=:newAddress WHERE id=:id";


        Query query = session.createQuery(hql);
        query.setParameter("newName",branch.getName());
        query.setParameter("newContact",branch.getContact());
        query.setParameter("newEmail",branch.getEmail());
        query.setParameter("newAddress",branch.getAddress());
        query.setParameter("id",branch.getId());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        return b;
    }
}

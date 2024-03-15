package lk.ijse.libraryManagementSystem.dao.custom.impl;

import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.custom.BranchDao;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDaoImpl implements BranchDao {

    @Override
    public Branch generateNewId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM Branch ORDER BY id DESC LIMIT 1";
        Query<Branch> query = session.createQuery(hql, Branch.class);
        List<Branch> list = query.list();
        session.close();
        if (list.isEmpty()) {
            return new Branch();
        } else {
            return list.get(0);
        }

    }

    @Override
    public boolean save(Branch branch) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String result = (String) session.save(branch);
        transaction.commit();
        session.close();
        System.out.println("result" + result);
        if (result == null) {
            System.out.println("result if" + result);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Branch> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = " FROM Branch ";
        Query<Branch> query = session.createQuery(hql, Branch.class);
        List<Branch> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean update(Branch branch) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Branch SET name=:newName, contact=:newContact, email=: newEmail,address=:newAddress WHERE id=:id";


        Query query = session.createQuery(hql);
        query.setParameter("newName", branch.getName());
        query.setParameter("newContact", branch.getContact());
        query.setParameter("newEmail", branch.getEmail());
        query.setParameter("newAddress", branch.getAddress());
        query.setParameter("id", branch.getId());
        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return b;
    }

    @Override
    public boolean delete(Branch branch) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
       /* String hql="DELETE Branch WHERE id=:id";


        Query query = session.createQuery(hql);
        query.setParameter("id",branch.getId());*/
        session.delete(branch);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public Branch getBranch(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
       /* String hql="DELETE Branch WHERE id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",branch.getId());*/
        Branch branch = (Branch) session.get( Branch.class,id);
        transaction.commit();
        session.close();
        return branch;
    }
}

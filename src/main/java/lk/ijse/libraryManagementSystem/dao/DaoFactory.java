package lk.ijse.libraryManagementSystem.dao;


import lk.ijse.libraryManagementSystem.dao.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {

    }
    public static DaoFactory getDaoFactory()  {
        return daoFactory == null ? daoFactory= new DaoFactory() : daoFactory;
    }
    public enum DAOTypes{
        USER,ADMIN,BOOK,TRANSACTION,BRANCH
    }
    public SuperDao getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDaoImpl();
            case ADMIN:
                return new AdminDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            case TRANSACTION:
                return new TransactionDaoImpl();
            case BRANCH:
                return new BranchDaoImpl();
            default:
                return null;

        }

    }

}

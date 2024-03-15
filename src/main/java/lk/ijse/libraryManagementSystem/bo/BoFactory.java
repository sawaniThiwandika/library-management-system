package lk.ijse.libraryManagementSystem.bo;


import lk.ijse.libraryManagementSystem.bo.impl.*;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }
    public static BoFactory getBOFactory()  {
        return boFactory == null ? boFactory= new BoFactory() : boFactory;
    }
    public enum BOTypes{
        USER,ADMIN,BOOK,TRANSACTION,BRANCH
    }
    public SuperBo getBo(BOTypes boTypes){
        switch (boTypes){

            case USER:
                return new UserBoImpl();
            case ADMIN:
                return new AdminBoImpl();
            case BOOK:
                return new BookBoImpl();
            case TRANSACTION:
                return new TransactionBoImpl();
            case BRANCH:
                return new BranchBoImpl();
            default:
                return null;

        }

    }
}

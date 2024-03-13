package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.TransactionBo;
import lk.ijse.libraryManagementSystem.dao.TransactionDao;
import lk.ijse.libraryManagementSystem.dao.impl.TransactionDaoImpl;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.entity.UserBookDetails;

import java.util.ArrayList;
import java.util.List;

public class TransactionBoImpl implements TransactionBo {
    TransactionDao transactionDao=new TransactionDaoImpl();
    @Override
    public String generateNewTransactionId() {

        UserBookDetails userBookDetails = transactionDao.generateNewId();
        if (!(userBookDetails.getId() == null)) {
            return splitId(userBookDetails.getId());
        }
        return splitId(null);
    }
    public String splitId(String currentTransactionId) {
        if(currentTransactionId!=null){
            String [] split= currentTransactionId.split("T0");
            int id=Integer.parseInt(split[1]);

            id++;
            if(id<10)
            {return "T00"+id;}
            else{
                return "T0"+id;
            }
        }
        else {
            return "T001";
        }
    }

    @Override
    public boolean saveTransaction(TransactionDto transactionDto) {

        boolean saved = transactionDao.save(new UserBookDetails(transactionDto.getId(), transactionDto.getUser(), transactionDto.getBook(), transactionDto.getReserveDate(), transactionDto.getReturnDate(), transactionDto.isReturn()));
        return saved;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {

        List<UserBookDetails> transactionList = transactionDao.getAll();
        ArrayList<TransactionDto> transactionDtos = new ArrayList<>();
        for (UserBookDetails detail : transactionList) {
            transactionDtos.add(new TransactionDto(detail.getId(), detail.getUser(), detail.getBook(), detail.getReserveDate(), detail.getReturnDate(), detail.isReturn()));
        }
        return transactionDtos;
    }

    @Override
    public boolean updateTransaction(TransactionDto transactionDto) {


        boolean updated = transactionDao.update(new UserBookDetails(transactionDto.getId(), transactionDto.getUser(), transactionDto.getBook(), transactionDto.getReserveDate(), transactionDto.getReturnDate(), transactionDto.isReturn()));
        return updated;
    }
    public boolean returnBook(TransactionDto transactionDto) {


        boolean updated = transactionDao.returnBook(new UserBookDetails(transactionDto.getId(), transactionDto.getUser(), transactionDto.getBook(), transactionDto.getReserveDate(), transactionDto.getReturnDate(), transactionDto.isReturn()));
        return updated;
    }
}

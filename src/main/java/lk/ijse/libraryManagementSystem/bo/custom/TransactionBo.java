package lk.ijse.libraryManagementSystem.bo.custom;

import lk.ijse.libraryManagementSystem.bo.SuperBo;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import org.hibernate.Session;

import java.util.List;

public interface TransactionBo extends SuperBo {
    public String generateNewTransactionId();
    public boolean saveTransaction( TransactionDto transactionDto);
    public List<TransactionDto> getAllTransactions();
    public boolean updateTransaction( TransactionDto transactionDto);
    public boolean returnBook(TransactionDto transactionDto);
    public List<TransactionDto> lateReturns();
}

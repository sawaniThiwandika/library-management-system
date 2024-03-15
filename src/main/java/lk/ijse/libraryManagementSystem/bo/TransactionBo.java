package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import org.hibernate.Session;

import java.util.List;

public interface TransactionBo {
    public String generateNewTransactionId();
    public boolean saveTransaction( TransactionDto transactionDto);
    public List<TransactionDto> getAllTransactions();
    public boolean updateTransaction( TransactionDto transactionDto);
    public boolean returnBook(TransactionDto transactionDto);
    public List<TransactionDto> lateReturns();
}

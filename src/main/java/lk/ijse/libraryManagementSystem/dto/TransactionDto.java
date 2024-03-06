package lk.ijse.libraryManagementSystem.dto;

import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {
    String id;
    private User user;
    private Book book;
    private LocalDate reserveDate;
    private LocalDate returnDate;
    private boolean isReturn;
}

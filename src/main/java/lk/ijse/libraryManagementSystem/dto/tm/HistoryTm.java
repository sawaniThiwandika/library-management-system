package lk.ijse.libraryManagementSystem.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryTm {
    String t_id;
    LocalDate reservationDate;
    LocalDate returnDate;
    String bookId;
    String bookName;
    String author;

}

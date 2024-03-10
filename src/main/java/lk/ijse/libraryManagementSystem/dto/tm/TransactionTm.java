package lk.ijse.libraryManagementSystem.dto.tm;

import javafx.scene.control.CheckBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionTm {

    String t_id;
    LocalDate reservationDate;
    LocalDate returnDate;
    String bookId;
    String bookName;
    String user_id;
    private CheckBox isReturn;


}

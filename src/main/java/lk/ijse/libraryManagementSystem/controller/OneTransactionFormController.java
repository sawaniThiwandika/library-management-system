package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.libraryManagementSystem.bo.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class OneTransactionFormController {

    @FXML
    private Button extensionBtn;

    @FXML
    private Button readBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Label txtAuthor;

    @FXML
    private Label txtReturnDate;

    @FXML
    private Label txtTitle;
    TransactionBo transactionBo=new TransactionBoImpl();
    TransactionDto dto;
    @FXML
    void extensionBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no= new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type1 = new Alert(Alert.AlertType.INFORMATION, "Do you want to return the book?", yes, no).showAndWait();

        if (type1.orElse(no) == yes) {
            boolean saved = transactionBo.updateTransaction(new TransactionDto(dto.getId(),
                    dto.getUser(), dto.getBook(), dto.getReserveDate(),
                    dto.getReturnDate().plusDays(14), false));


            if (saved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Extension Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Can not Extension").show();
            }
        }

    }

    @FXML
    void readBtnOnAction(ActionEvent event) {


    }

    @FXML
    void returnBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no= new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type1 = new Alert(Alert.AlertType.INFORMATION, "Do you want to return the book?", yes, no).showAndWait();

        if (type1.orElse(no) == yes) {
            boolean saved = transactionBo.returnBook(new TransactionDto(dto.getId(),
                    dto.getUser(),dto.getBook(), dto.getReserveDate(),
                    dto.getReturnDate(), true));


            if (saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Return Success").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Can not Return").show();
            }

        }
    }
    public void initialize(TransactionDto dto) throws SQLException, IOException {
        this.dto=dto;
        txtTitle.setText(this.dto.getBook().getTitle());
        txtAuthor.setText(this.dto.getBook().getAuthor());
        txtReturnDate.setText(String.valueOf(this.dto.getReturnDate()));


    }
}

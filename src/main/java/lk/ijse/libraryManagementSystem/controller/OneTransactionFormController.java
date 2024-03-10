package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        boolean saved = transactionBo.updateTransaction(new TransactionDto(dto.getId(),
                dto.getUser(),dto.getBook(), dto.getReserveDate(),
                dto.getReturnDate().plusDays(14), false));


        if (saved){

            new Alert(Alert.AlertType.CONFIRMATION,"Extension Success").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Can not Extension").show();
        }

    }

    @FXML
    void readBtnOnAction(ActionEvent event) {

    }

    @FXML
    void returnBtnOnAction(ActionEvent event) {

    }
    public void initialize(TransactionDto dto) throws SQLException, IOException {
        this.dto=dto;
        txtTitle.setText(this.dto.getBook().getTitle());
        txtAuthor.setText(this.dto.getBook().getAuthor());
        txtReturnDate.setText(String.valueOf(this.dto.getReturnDate()));


    }
}

package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;

import java.io.IOException;
import java.sql.SQLException;

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

    @FXML
    void extensionBtnOnAction(ActionEvent event) {

    }

    @FXML
    void readBtnOnAction(ActionEvent event) {

    }

    @FXML
    void returnBtnOnAction(ActionEvent event) {

    }
    public void initialize(TransactionDto dto) throws SQLException, IOException {
        txtTitle.setText(dto.getBook().getTitle());
        txtAuthor.setText(dto.getBook().getAuthor());
        txtReturnDate.setText(String.valueOf(dto.getReturnDate()));


    }
}

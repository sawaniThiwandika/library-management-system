package lk.ijse.libraryManagementSystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lk.ijse.libraryManagementSystem.bo.custom.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.custom.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShelfFormController {
    @FXML
    private VBox vBox;
    UserDto userDto;
    TransactionBo transactionBo=new TransactionBoImpl();

    public void initialize() throws SQLException, IOException {
       userDto=LoginFormController.dto;
        loadAll();


    }

    private void loadAll() throws IOException, SQLException {
        List<TransactionDto> allTransactions = transactionBo.getAllTransactions();
        for (TransactionDto dto:allTransactions
             ) {
            if (dto.getUser().getEmail().equals(userDto.getEmail())){
                if(!dto.isReturn()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/one_transaction_form.fxml"));
                    Parent smallPane = loader.load();
                    OneTransactionFormController smallController = loader.getController();
                    smallController.initialize(dto);
                    vBox.getChildren().add(smallPane);
                }
            }

        }


    }



}

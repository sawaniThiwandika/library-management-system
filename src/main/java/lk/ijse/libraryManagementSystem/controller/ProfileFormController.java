package lk.ijse.libraryManagementSystem.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.libraryManagementSystem.bo.custom.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.custom.UserBo;
import lk.ijse.libraryManagementSystem.bo.custom.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.bo.custom.impl.UserBoImpl;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.dto.tm.HistoryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProfileFormController {

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colReservstionDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colTID;


    @FXML
    private Button removeBtn;

    @FXML
    private TableView<HistoryTm> tableHistory;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtContact;

    @FXML
    private Button updateBtn;
    UserBo userBo=new UserBoImpl();
    private ObservableList<HistoryTm> obListHistory = FXCollections.observableArrayList();
    TransactionBo transactionBo=new TransactionBoImpl();
    public void initialize() throws SQLException, IOException{

        UserDto user = userBo.searchUser(LoginFormController.dto.getEmail());
        txtContact.setText(user.getContact());
        txtPassword.setText(user.getPassword());
        txtUserName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        loadHistory();
        setCellValueFactory();

    }

    private void loadHistory() {

        List<TransactionDto> allTransactions = transactionBo.getAllTransactions();

        for (int i=0;i<allTransactions.size();i++){
            if (allTransactions.get(i).getUser().getEmail().equals(LoginFormController.dto.getEmail())&&allTransactions.get(i).isReturn()){
                obListHistory.add(new HistoryTm(allTransactions.get(i).getId(),allTransactions.get(i).getReserveDate(),
                        allTransactions.get(i).getReturnDate(),allTransactions.get(i).getBook().getId(),
                        allTransactions.get(i).getBook().getTitle(),allTransactions.get(i).getBook().getAuthor()));
            }
        }
        tableHistory.setItems(obListHistory);

    }

    private void setCellValueFactory() {
        colReservstionDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTID.setCellValueFactory(new PropertyValueFactory<>("t_id"));
    }

    @FXML
    void removeBtnOnAction(ActionEvent event) {
        UserDto userDto = userBo.searchUser(txtEmail.getText());
        boolean checkHaveReturns=checkIncompleteReturns();
        if (checkHaveReturns){
            new Alert(Alert.AlertType.ERROR,"Can not delete. Please return your book before delete account").show();
        }
        else {
            boolean deleteUser = userBo.deleteUser(userDto);
            if (deleteUser){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully deleted").show();
                System.exit(0);
            }
            else  {
                new Alert(Alert.AlertType.ERROR,"Can not delete").show();
            }
        }



    }

    private boolean checkIncompleteReturns() {

        List<TransactionDto> allTransactions = transactionBo.getAllTransactions();
        for (int i = 0; i < allTransactions.size(); i++) {
            if (allTransactions.get(i).getUser().getEmail().equals(LoginFormController.dto.getEmail())&& !allTransactions.get(i).isReturn()) {
              return true;
            }
        }
        return false;
    }
    @FXML
    void searchBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

        String contact;
        String textEmail = txtEmail.getText();
        String nameText = txtUserName.getText();
        String passwordText = txtPassword.getText();
        String contactText = txtContact.getText();
        boolean updateUser = userBo.updateUser(new UserDto(nameText, textEmail, LoginFormController.dto.getBranch(), passwordText, LoginFormController.dto.getTransactions(), contactText));
        if(updateUser){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully updated").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Cannot update").show();
        }

    }
}

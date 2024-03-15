package lk.ijse.libraryManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.libraryManagementSystem.bo.custom.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.custom.UserBo;
import lk.ijse.libraryManagementSystem.bo.custom.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.bo.custom.impl.UserBoImpl;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.dto.tm.HistoryTm;

import java.util.List;

public class HistoryFormController {

    @FXML
    private TableColumn<?, ?> colTID;
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
    private TableView<HistoryTm> tableHistory;

    @FXML
    private TextField txtSearch;
    UserBo userBo=new UserBoImpl();
    private ObservableList<HistoryTm> obListHistory = FXCollections.observableArrayList();
    TransactionBo transactionBo=new TransactionBoImpl();
    @FXML
    void searchBtnOnAction(ActionEvent event) {


    }

    public void initialize(UserDto dto) {
       loadHistory(dto);
       setCellValueFactory();
    }
    private void loadHistory(UserDto dto) {

        List<TransactionDto> allTransactions = transactionBo.getAllTransactions();

        for (int i=0;i<allTransactions.size();i++){
           if(dto.getEmail().equals(allTransactions.get(i).getUser().getEmail())){
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
}

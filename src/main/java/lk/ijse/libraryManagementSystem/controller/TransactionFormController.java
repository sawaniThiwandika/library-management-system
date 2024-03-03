package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionFormController {

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colIsReturn;

    @FXML
    private TableColumn<?, ?> colReservationDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUserEmail;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private Label labelCount;

    @FXML
    private Label labelLate;

    @FXML
    private Label labelOngoing;

    @FXML
    private TableView<?> tableTransaction;

    @FXML
    private ComboBox<?> comboBranch;

    public void comboBranchOnAction(ActionEvent actionEvent) {

    }
}

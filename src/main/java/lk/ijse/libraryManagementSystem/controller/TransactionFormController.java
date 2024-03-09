package lk.ijse.libraryManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

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
    private ComboBox<String> comboBranch;

    public void initialize(){
        setComboBoxValues();
    }

    private void setComboBoxValues() {
        ObservableList<String> types = FXCollections.observableArrayList();
        comboBranch.setItems(types);
    }

    public void comboBranchOnAction(ActionEvent actionEvent) {

    }

}

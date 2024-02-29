package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HistoryFormController {

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
    private TableView<?> tableHistory;

    @FXML
    private TextField txtSearch;

    @FXML
    void searchBtnOnAction(ActionEvent event) {


    }

}

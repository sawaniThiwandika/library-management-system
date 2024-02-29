package lk.ijse.libraryManagementSystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private Button removeBtn;

    @FXML
    private TableView<?> tableHistory;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button updateBtn;

    @FXML
    void removeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }
}

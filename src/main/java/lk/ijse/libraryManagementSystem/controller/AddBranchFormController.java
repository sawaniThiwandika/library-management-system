package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AddBranchFormController {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label labelAddNewBranch;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }
}

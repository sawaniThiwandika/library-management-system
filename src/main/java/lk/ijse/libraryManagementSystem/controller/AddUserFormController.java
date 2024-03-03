package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserFormController {

    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane addUser;

    @FXML
    private Button backBtn;

    @FXML
    private JFXComboBox<?> comboBranch;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void addButtonOnAction(ActionEvent event) {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) addUser.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("login");
        stage.centerOnScreen();
    }

    @FXML
    void comboBranchOnAction(ActionEvent event) {

    }



}

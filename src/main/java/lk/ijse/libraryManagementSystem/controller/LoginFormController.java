package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private Label forgetPasswordBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label lableHaveAccount;

    @FXML
    private AnchorPane loginPage;
    String type;
    public void initialize(String type) throws SQLException, IOException {
        this.type=type;
     if (type.equals("Admin")){

         registerBtn.setDisable(true);
         registerBtn.setVisible(false);
         lableHaveAccount.setVisible(false);
     }


    }
    @FXML
    void forgetPasswordButtonOnAction(MouseEvent event) {

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException {
        if(type.equals("Admin")){
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form_admin.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) loginPage.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("dashboard");
            stage.centerOnScreen();
        }
        else {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form_user.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) loginPage.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("dashboard");
            stage.centerOnScreen();
        }


    }

    @FXML
    void registerButtonOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/add_user_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) loginPage.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("add user");
        stage.centerOnScreen();
    }
}

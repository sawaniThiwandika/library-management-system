package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class SelectFormController {

    @FXML
    private Button loginButton;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioUser;
    @FXML
    private AnchorPane selectForm;


    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException, SQLException {
        if(radioAdmin.isSelected()==true){
            URL resource = this.getClass().getResource("/view/login_form.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent load = fxmlLoader.load();
            LoginFormController controller = fxmlLoader.getController();
            controller.initialize("Admin");
            Stage stage = new Stage();
            stage.setTitle("Update customer");
            stage.setScene(new Scene(load));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.show();
        }
        else {
            URL resource = this.getClass().getResource("/view/login_form.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent load = fxmlLoader.load();
            LoginFormController controller = fxmlLoader.getController();
            controller.initialize("User");
            Stage stage = new Stage();
            stage.setTitle("Login User");
            stage.setScene(new Scene(load));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.show();
        }
    }
    @FXML
    void radioAdminOnAction(ActionEvent event) {
        if(radioAdmin.isSelected()==true){
            radioUser.setSelected(false);
        }
    }

    @FXML
    void radioUserOnAction(ActionEvent event) {
        if(radioUser.isSelected()==true){
            radioAdmin.setSelected(false);
        }
    }

}

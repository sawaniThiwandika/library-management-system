package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.AdminBo;
import lk.ijse.libraryManagementSystem.bo.UserBo;
import lk.ijse.libraryManagementSystem.bo.impl.AdminBoImpl;
import lk.ijse.libraryManagementSystem.bo.impl.UserBoImpl;
import lk.ijse.libraryManagementSystem.dto.AdminDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFormController {

    @FXML
    private Label forgetPasswordBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton registerBtn;


    @FXML
    private Label lableHaveAccount;

    @FXML
    private AnchorPane loginPage;
    @FXML
    private Button hidePwdBtn;

    @FXML
    private Button hideUserNameBtn;
    @FXML
    private PasswordField pwdField;
    @FXML
    private Label labelPassword;

    @FXML
    private Label labelUserName;
     static String type;
     static UserDto dto;
     UserBo userBo=new UserBoImpl();
    AdminBo adminBo=new AdminBoImpl();
    public void initialize(String type) throws SQLException, IOException {
        this.type=type;
     if (type.equals("Admin")){

         registerBtn.setDisable(true);
         registerBtn.setVisible(false);
         lableHaveAccount.setVisible(false);
     }


    }
    @FXML
    private PasswordField userNameField;


    @FXML
    void hidePwdBtnOnAction(ActionEvent event) {
       labelPassword.setText(pwdField.getText());
        pwdField.setVisible(!pwdField.isVisible());


    }

    @FXML
    void hideUserNameBtnOnAction(ActionEvent event) {
       labelUserName.setText(userNameField.getText());
        userNameField.setVisible(!userNameField.isVisible());
    }
    @FXML
    void forgetPasswordButtonOnAction(MouseEvent event) {

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException {


        boolean checked = checkCredentialsUser();
        boolean checkedAdmin = checkCredentialsAdmin();
        System.out.println("checkUser1: "+checked);
        if(type.equals("User")){
            System.out.println("checkUser2: "+checked);
            if(checked){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form_user.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) loginPage.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("dashboard");
                stage.centerOnScreen();

            }
        }


        if(type.equals("Admin")){
            if(checkedAdmin){
                System.out.println("checkAdmin2: "+checked);
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form_admin.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) loginPage.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("dashboard");
                stage.centerOnScreen();
            }

        }

    }


    private boolean checkCredentialsAdmin() {

     String userName= userNameField.getText();
        String pwdText= pwdField.getText();

        ArrayList<AdminDto> allAdmins = adminBo.getAllAdmins();
        for (AdminDto adminDto: allAdmins){
            if(adminDto.getEmail().equals(userName)){
                if(adminDto.getPassword().equals(pwdText)){

                    return true;

                }
            }
        }
        return false;
    }

    private boolean checkCredentialsUser() {
        String userName= userNameField.getText();
        String pwdText= pwdField.getText();

        ArrayList<UserDto> allUsers = userBo.getAllUsers();
        for (UserDto userDto: allUsers){
            if(userDto.getEmail().equals(userName)){
                if(userDto.getPassword().equals(pwdText)){
                    dto=userDto;
                    return true;

                }
            }
        }
        return false;
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

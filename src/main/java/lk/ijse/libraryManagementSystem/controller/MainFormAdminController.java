package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class MainFormAdminController {

    @FXML
    private Button bookBtn;

    @FXML
    private Button branchBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button staffBtn;

    @FXML
    private Button transcationBtn;

    @FXML
    private Button userBtn;
    public void initialize() throws SQLException, IOException{
        setForms("/view/dashboard_admin_form.fxml");
    }
    public void setForms(String forms) throws IOException {
        String[] form = {"/view/dashboard_admin_form.fxml",
                "/view/book_form.fxml",
                "/view/user_form.fxml",
                "/view/transaction_form.fxml",
                "/view/branch_form.fxml",
                "/view/staff_form.fxml"

        };

        Button[] btn = {dashboardBtn,bookBtn,userBtn,transcationBtn,branchBtn,staffBtn};
        AnchorPane load = FXMLLoader.load(getClass().getResource(forms));
        mainForm.getChildren().clear();
        mainForm.getChildren().add(load);

        for (int i = 0; i < form.length; i++) {
            btn[i].setStyle("-fx-background-color:  #232C37");
            if (forms.equals(form[i])) {
                btn[i].setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
            }
        }

    }
    @FXML
    void bookBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/book_form.fxml");
    }

    @FXML
    void branchBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/branch_form.fxml");

    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/dashboard_admin_form.fxml");
    }

    @FXML
    void staffBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/staff_form.fxml");
    }

    @FXML
    void transactionBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/transaction_form.fxml");
    }

    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/user_form.fxml");
    }


}

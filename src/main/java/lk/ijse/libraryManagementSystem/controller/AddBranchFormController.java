package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.bo.impl.BranchBoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private TextField txtName;
    BranchBo branchBo= new BranchBoImpl();
    public void initialize() throws SQLException, IOException{
        generateNextId();
        labelDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextId() {
        String branchId = branchBo.generateNewBranchId();
        labelId.setText(branchId);
    }

    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        String addressText = txtAddress.getText();
        String contactText = txtContact.getText();
        String emailText = txtEmail.getText();
        String nameText = txtName.getText();
        String idText = labelId.getText();
        boolean saved = branchBo.saveBranch(new BranchDto(idText, nameText, addressText, contactText, emailText,new ArrayList<>(),new ArrayList<>()));
        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Can not Save").show();
        }

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }
}

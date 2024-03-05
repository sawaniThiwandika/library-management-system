package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.bo.UserBo;
import lk.ijse.libraryManagementSystem.bo.impl.BranchBoImpl;
import lk.ijse.libraryManagementSystem.bo.impl.UserBoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.Branch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddUserFormController {

    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane addUser;

    @FXML
    private Button backBtn;

    @FXML
    private JFXComboBox<String> comboBranch;

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
     BranchBo branchBo=new BranchBoImpl();
    UserBo userBo=new UserBoImpl();
    public void initialize() throws SQLException, IOException {
        generateNextId();
        List<BranchDto> branchDtos = loadAllBranchers();
        setBranchComboBoxValues(branchDtos);
    }

    private void setBranchComboBoxValues(List<BranchDto> branchDtos) {
        ObservableList<String> obList = FXCollections.observableArrayList();


        for (BranchDto dto : branchDtos) {
            obList.add(String.valueOf(dto.getName()));
        }
        comboBranch.setItems(obList);
    }

    private List<BranchDto> loadAllBranchers() {
     return branchBo.loadAllBranch();
    }

    private void generateNextId() {
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String nameText = txtUserName.getText();
        String emailText = txtEmail.getText();
        String contactText = txtContact.getText();
        String textPassword = txtPassword.getText();
        String branchValue = comboBranch.getValue();
        BranchDto branch = getBranch(branchValue);
        boolean saved = userBo.saveUser(new UserDto(nameText, emailText, new Branch(branch.getId(), branch.getName(), branch.getAddress(), branch.getContact(), branch.getEmail(), branch.getUsers(), branch.getBooks()), textPassword, new ArrayList<>(), contactText));
        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Can not Save").show();
        }

    }

    private BranchDto getBranch(String branchValue) {
        List<BranchDto> branchDtos = loadAllBranchers();
        for (BranchDto dto: branchDtos){
            if(branchValue.equals(dto.getName())){
                return dto;
            }
        }
        return null;
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

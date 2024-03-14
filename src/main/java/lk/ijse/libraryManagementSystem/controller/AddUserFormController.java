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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        boolean isvalid = validationUser();
        if(isvalid) {
            String nameText = txtUserName.getText();
            String emailText = txtEmail.getText();
            String contactText = txtContact.getText();
            String textPassword = txtPassword.getText();
            String branchValue = comboBranch.getValue();
            BranchDto branch = getBranch(branchValue);
            boolean saved = userBo.saveUser(new UserDto(nameText, emailText, new Branch(branch.getId(), branch.getName(), branch.getAddress(), branch.getContact(), branch.getEmail(), branch.getUsers(), branch.getBooks()), textPassword, new ArrayList<>(), contactText));
            if (saved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Can not Save").show();
            }
        }

    }

    private boolean validationUser() {
        String name = txtUserName.getText();
        if(txtUserName.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"User name field empty").show();
            return false;

        }

        Pattern compile = Pattern.compile("^[A-Za-z]+(?:[ '-][A-Za-z]+)*$");
        Matcher matcher = compile.matcher(txtUserName.getText());
        boolean matchName = matcher.matches();
        System.out.println(matchName);
        if(!matchName){
            new Alert(Alert.AlertType.ERROR,"Invalid user name").show();
            return  false;
        }


        String email = txtEmail.getText();
        if(email.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Email address field empty").show();
            return false;

        }
        boolean matchEmail= Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" +
                "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",email);
        if(!matchEmail){
            new Alert(Alert.AlertType.ERROR,"Invalid email").show();
            return  false;
        }

        String contact = txtContact.getText();
        if(contact.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"contact field empty").show();
            return false;

        }
        boolean matchContact= Pattern.matches("^0[1-9]\\d{8}$",contact);
        if(!matchContact){
            new Alert(Alert.AlertType.ERROR,"Invalid contact number").show();
            return  false;
        }
        if(comboBranch.getValue() == null){
            new Alert(Alert.AlertType.ERROR,"please select a branch").show();
            return false;

        }
        if(txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"please give a password").show();
            return false;

        }
        if(txtConfirmPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"confirm the password").show();
            return false;

        }
        if(!txtConfirmPassword.getText().equals(txtPassword.getText())){
            new Alert(Alert.AlertType.ERROR,"Confirm your password").show();
            return false;

        }

        return true;

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

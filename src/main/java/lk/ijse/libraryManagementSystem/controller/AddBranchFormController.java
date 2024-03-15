package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.libraryManagementSystem.bo.BoFactory;
import lk.ijse.libraryManagementSystem.bo.custom.BranchBo;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
    BranchBo branchBo= (BranchBo) BoFactory.getBOFactory().getBo(BoFactory.BOTypes.BRANCH);
    BranchDto branchDto;
    public void initialize() throws SQLException, IOException{
        generateNextId();
        labelDate.setText(String.valueOf(LocalDate.now()));
    }
    public void initialize(BranchDto dto) {
        branchDto=dto;
        labelAddNewBranch.setText("Update Branch Details");
        addBtn.setText("Update");
        labelId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtContact.setText(dto.getContact());
        txtAddress.setText(dto.getAddress());

    }

    private void generateNextId() {
        String branchId = branchBo.generateNewBranchId();
        labelId.setText(branchId);
    }
    private boolean checkDuplicates() {
        List<BranchDto> branchDtos = branchBo.loadAllBranch();
        for (int i=0; i<branchDtos.size();i++){
            if(branchDtos.get(i).getId().equals(labelId.getText())){
                return true;
            }
        }
        return false;

    }
    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        boolean isValid=validateBranch();
        if (isValid) {
            boolean checked = checkDuplicates();
            if (checked) {

                String addressText = txtAddress.getText();
                String contactText = txtContact.getText();
                String emailText = txtEmail.getText();
                String nameText = txtName.getText();
                String idText = labelId.getText();
                boolean saved = branchBo.updateBranch(new BranchDto(idText, nameText, addressText, contactText, emailText, branchDto.getUsers(), branchDto.getBooks()));
                if (saved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Can not Update").show();
                }
            } else {
                String addressText = txtAddress.getText();
                String contactText = txtContact.getText();
                String emailText = txtEmail.getText();
                String nameText = txtName.getText();
                String idText = labelId.getText();
                boolean saved = branchBo.saveBranch(new BranchDto(idText, nameText, addressText, contactText, emailText, new ArrayList<>(), new ArrayList<>()));
                if (saved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Can not Save").show();
                }
            }
        }


    }

    private boolean validateBranch() {
        String name=txtName.getText();
        if(name.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Branch name field empty").show();
            return false;
        }
        if(txtAddress.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Branch Address field empty").show();
            return false;
        }
        boolean matchAddress = Pattern.matches("^[0-9A-Za-z\\s.,#-]+$",txtAddress.getText());
        if(!matchAddress){
            new Alert(Alert.AlertType.ERROR,"Invalid address").show();
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


        return true;
    }


    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }


}

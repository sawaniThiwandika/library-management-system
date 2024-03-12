package lk.ijse.libraryManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.bo.impl.BranchBoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.dto.tm.BranchTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BranchFormController {


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<BranchTm> tableBranch;

    @FXML
    private Button btnAdd;
    BranchBo branchBo=new BranchBoImpl();
    ObservableList<BranchTm> tableValues = FXCollections.observableArrayList();
    public void initialize() throws SQLException, IOException{
        loadBranches();
        setCellValueFactory();

    }

    private void loadBranches(){
        List<BranchDto> allBranch = branchBo.loadAllBranch();
        for (int i=0;i<allBranch.size();i++){
            Button btnD=new Button("Delete");
            Button btnUpdate=new Button("Update");
            tableValues.add(new BranchTm(allBranch.get(i).getId(), allBranch.get(i).getAddress(), allBranch.get(i).getContact(), allBranch.get(i).getEmail(), btnUpdate, btnD));
            deleteBranchButtonOnAction(btnD,allBranch.get(i));
            updateBranchButtonOnAction(btnUpdate,allBranch.get(i));
            btnUpdate.setCursor(Cursor.HAND);
            btnD.setCursor(Cursor.HAND);
        }

        tableBranch.setItems(tableValues);

    }

    private void updateBranchButtonOnAction(Button btnUpdate, BranchDto dto) {


        btnUpdate.setOnAction(event -> {
            try {

                openUpdateBranchForm(dto);


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void openUpdateBranchForm(BranchDto dto) throws IOException, SQLException {
        URL resource = this.getClass().getResource("/view/add_branch_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddBranchFormController controller = fxmlLoader.getController();
        controller.initialize(dto);
        Stage stage = new Stage();
        stage.setTitle("Update Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(true);
        stage.show();

    }
    private void deleteBranchButtonOnAction(Button btnD,BranchDto dto) {
        btnD.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tableBranch.getSelectionModel().getSelectedIndex();
                BranchTm branchTm = tableBranch.getSelectionModel().getSelectedItem();
                BranchDto branch = branchBo.getBranch(branchTm.getId());
                boolean isDeleted = branchBo.deleteBranch(branch);
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"SUCCESSFULLY deleted").show();
                }
                tableValues.remove(focusedIndex);
                System.out.println(focusedIndex);
                tableBranch.refresh();

            }
        });
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
       colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/add_branch_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

}

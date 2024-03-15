package lk.ijse.libraryManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.UserBo;
import lk.ijse.libraryManagementSystem.bo.impl.UserBoImpl;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.dto.tm.UserTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFormController {

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colHistory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colUpdate;



    @FXML
    private Label labelCount;

    @FXML
    private TableView<UserTm> tableUsers;
    UserBo userBo=new UserBoImpl();
    private ObservableList<UserTm> obList = FXCollections.observableArrayList();
    public void initialize() throws SQLException, IOException{
        loadAllCustomers();;
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("number"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colHistory.setCellValueFactory(new PropertyValueFactory<>("viewHistory"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

    }

    private void loadAllCustomers() throws SQLException, IOException {
        int i=1;

        ArrayList<UserDto> allUsers = userBo.getAllUsers();

        for (UserDto dto:allUsers) {
            Button btnViewHistory=new Button("View History");
            Button btnU=new Button("Update");
            Button btnD=new Button("Delete");
            obList.add(new UserTm(i,dto.getName(),dto.getEmail(),dto.getContact(),btnViewHistory,btnU,btnD));
            i++;
            deleteUserButtonOnAction(btnD);
            updateButtonOnAction(btnU,dto);
            viewButtonOnAction(btnViewHistory,dto);
            btnU.setCursor(Cursor.HAND);
            btnD.setCursor(Cursor.HAND);
            btnViewHistory.setCursor(Cursor.HAND);
        }
        tableUsers.setItems(obList);
    }

    private void updateButtonOnAction(Button btnU, UserDto dto) {
        btnU.setOnAction(event -> {
            try {


                openUpdateUserForm(dto);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void viewButtonOnAction(Button btnView, UserDto dto) {
        btnView.setOnAction(event -> {
            try {
                openViewTransationForm(dto);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void openViewTransationForm(UserDto dto) throws IOException {
        URL resource = this.getClass().getResource("/view/history_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        HistoryFormController controller = fxmlLoader.getController();
        controller.initialize(dto);
        Stage stage = new Stage();
        stage.setTitle("view History");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(true);
        stage.show();
    }

    private void openUpdateUserForm(UserDto dto) throws IOException {
        URL resource = this.getClass().getResource("/view/add_user_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddUserFormController controller = fxmlLoader.getController();
        controller.initialize(dto);
        Stage stage = new Stage();
        stage.setTitle("Update User");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(true);
        stage.show();
    }

    private void deleteUserButtonOnAction(Button btnD) {

    }

}

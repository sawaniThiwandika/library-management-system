package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddBookFormController {

    @FXML
    private JFXComboBox<?> comboBranch;

    @FXML
    private Button addBtn;

    @FXML
    private Button addImageBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private JFXComboBox<?> comboGenre;

    @FXML
    private Label labelAddNewBook;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelId;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtTitle;
    public void initialize() throws SQLException, IOException {
        generateNextId();
        labelDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextId() {

    }

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @FXML
    void addImageBtnOnAction(ActionEvent event) {

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void comboGenreOnAction(ActionEvent event) {

    }

    public void comboBranchOnAction(ActionEvent actionEvent) {
        
    }
}

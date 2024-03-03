package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class MainFormUserController {

    @FXML
    private Button history;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane mainFormUser;

    @FXML
    private Button profileBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button shelfBtn;
    public void initialize() throws SQLException, IOException {
        setForms("/view/book_form.fxml");
    }
    public void setForms(String forms) throws IOException {
        String[] form = {"/view/book_form.fxml",
                "/view/profile_form.fxml",
                "/view/shelf_form.fxml",
                "/view/settings_form.fxml",
                "/view/history_form.fxml"


        };

        Button[] btn = {homeBtn,profileBtn,shelfBtn,settingsBtn,history};
        AnchorPane load = FXMLLoader.load(getClass().getResource(forms));
        mainFormUser.getChildren().clear();
        mainFormUser.getChildren().add(load);

        for (int i = 0; i < form.length; i++) {
            btn[i].setStyle("-fx-background-color:  #232C37");
            if (forms.equals(form[i])) {
                btn[i].setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
            }
        }

    }
    @FXML
    void historyBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/history_form.fxml");
    }

    @FXML
    void homeBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/book_form.fxml");
    }

    @FXML
    void profileBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/profile_form.fxml");
    }

    @FXML
    void settingsBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/profile_form.fxml");
    }

    @FXML
    void shelfBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/shelf_form.fxml");
    }
}

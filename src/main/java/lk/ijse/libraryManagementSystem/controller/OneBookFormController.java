package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.dto.BookDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class OneBookFormController {

    @FXML
    private JFXButton btnBorrow;

    @FXML
    private Label labelName;

    @FXML
    private Label lableAvailability;

    @FXML
    private JFXButton moreBtn;
    @FXML
    private ImageView imageView;
    BookDto bookDto;
    public void initialize(BookDto dto) throws SQLException, IOException {

        labelName.setText(dto.getTitle());
        if(dto.isAvailable()){
            lableAvailability.setText("Available");
        }
        else {
            lableAvailability.setText("Not Available");
        }
        Image image=new Image("file:" + dto.getImagePath());
        imageView.setImage(image);
        moreBtn.setVisible(false);
        moreBtn.setDisable(true);

    }



    @FXML
    void btnBorrowOnAction(ActionEvent event) {


    }

    @FXML
    void moreBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/add_book_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddBookFormController controller = fxmlLoader.getController();
        controller.setValues(this.bookDto);
        Stage stage = new Stage();
        stage.setTitle("Book details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }

    public void initialize(String admin, BookDto bookDto) {
        this.bookDto=bookDto;
        System.out.println("this Id"+this.bookDto.getId());
        labelName.setText(bookDto.getTitle());
        if(bookDto.isAvailable()){
            lableAvailability.setText("Available");
        }
        else {
            lableAvailability.setText("Not Available");
        }
        Image image=new Image("file:" + bookDto.getImagePath());
        imageView.setImage(image);
        btnBorrow.setVisible(false);
        btnBorrow.setDisable(true);
    }
}

package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.libraryManagementSystem.dto.BookDto;

import java.io.IOException;
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
    void moreBtnOnAction(ActionEvent event) {

    }

    public void initialize(String admin, BookDto bookDto) {
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

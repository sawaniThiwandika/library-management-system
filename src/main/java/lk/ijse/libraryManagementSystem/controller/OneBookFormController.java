package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

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
    TransactionBo transactionBo=new TransactionBoImpl();
    public void initialize(BookDto dto) throws SQLException, IOException {
        bookDto=dto;

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
        UserDto userDto = LoginFormController.dto;
        boolean saved = transactionBo.saveTransaction(new TransactionDto(transactionBo.generateNewTransactionId(), new User(userDto.getName(), userDto.getEmail(), userDto.getBranch(), userDto.getPassword(), userDto.getTransactions(), userDto.getContact()), new Book(bookDto.getId(), bookDto.getBranch(), bookDto.getTransactions(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getImagePath(), bookDto.isAvailable()), LocalDate.now(), LocalDate.now().plusDays(14), false));
        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Can not Save").show();
        }




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

package lk.ijse.libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.libraryManagementSystem.bo.BookBo;
import lk.ijse.libraryManagementSystem.bo.impl.BookBoImpl;
import lk.ijse.libraryManagementSystem.dto.BookDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class BookFormController {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button addBtn;
    BookBo bookBo=new BookBoImpl();
    String type="user";

    public void setType(String type) {
        this.type = type;
    }

    public void initialize() throws SQLException, IOException {
        type=LoginFormController.type;
        loadAllBooks();


    }

    private void loadAllBooks() throws IOException, SQLException {
        List<BookDto> bookDtos = bookBo.loadAllBook();

        int y=0; int x=0;
        for (int i=0;i<bookDtos.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/one_book_form.fxml"));
            Parent smallPane = loader.load();
            OneBookFormController smallController = loader.getController();
            if (type.equals("Admin")){
                smallController.initialize("Admin",bookDtos.get(i));
            }
            else{
                smallController.initialize(bookDtos.get(i));
            }

            if(x/4==1){x=0;y=y+1;}
            gridPane.add(smallPane, x, y);
            x++;
        }

    }

    public void addBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/add_book_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}

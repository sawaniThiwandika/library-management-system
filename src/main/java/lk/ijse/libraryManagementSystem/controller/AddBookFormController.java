package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import lk.ijse.libraryManagementSystem.bo.BoFactory;
import lk.ijse.libraryManagementSystem.bo.custom.BookBo;
import lk.ijse.libraryManagementSystem.bo.custom.BranchBo;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.entity.Branch;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddBookFormController {

    @FXML
    private JFXComboBox<String> comboBranch;

    @FXML
    private Button addBtn;

    @FXML
    private Button addImageBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private JFXComboBox<String> comboGenre;

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
    BookBo bookBo= (BookBo) BoFactory.getBOFactory().getBo(BoFactory.BOTypes.BOOK);
    BranchBo branchBo= (BranchBo) BoFactory.getBOFactory().getBo(BoFactory.BOTypes.BRANCH);
    String photoPath;
    BookDto dto;
    public void initialize() throws SQLException, IOException {
        generateNextId();
        labelDate.setText(String.valueOf(LocalDate.now()));
        setComboBoxValues();

    }
    public void setValues(BookDto bookDto){
      this.dto=bookDto;
        txtTitle.setText(dto.getTitle());
        txtAuthor.setText(dto.getAuthor());
        labelId.setText(dto.getId());
        comboGenre.setValue(dto.getGenre());
        comboBranch.setValue(bookDto.getBranch().getName());
        photoPath=bookDto.getImagePath();
        labelAddNewBook.setText("Book Details");
        addBtn.setText("Update");
        cancelBtn.setText("Delete");
    } public BookDto getValues(){
        return dto;
    }

    private void setComboBoxValues() {
        ObservableList< String> genres = FXCollections.observableArrayList("Fiction","Non-Fiction","Children's and Young Adult","Poetry","Drama/Play","Science and Nature","Graphic Novels/Comics");
        comboGenre.setItems(genres);
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<BranchDto> branchDtos = loadAllBranchers();
        for (BranchDto dto : branchDtos) {
            obList.add(String.valueOf(dto.getName()));
        }
        comboBranch.setItems(obList);

    }

    private List<BranchDto> loadAllBranchers() {
        return branchBo.loadAllBranch();
    }

    private void generateNextId() {
        String bookId = bookBo.generateNewBookId();
        labelId.setText(bookId);


    }

    @FXML
    void addBtnOnAction(ActionEvent event) {



        boolean isValid= validateBook();
        if(isValid) {
            boolean checked = checkDuplicates();
            if (checked) {

                String labelIdText = labelId.getText();
                String text = txtTitle.getText();
                String authorText = txtAuthor.getText();
                String branchValue = comboBranch.getValue();
                String genreValue = comboGenre.getValue();
                String path = photoPath;
                BranchDto branchDto = getBranch(branchValue);
                boolean saved = bookBo.updateBook(new BookDto(labelIdText, new Branch(branchDto.getId(), branchDto.getName(), branchDto.getAddress(), branchDto.getContact(), branchDto.getEmail(), branchDto.getUsers(), branchDto.getBooks()), new ArrayList<>(), text, authorText, genreValue, path, true));
                if (saved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Can not Update").show();
                }
            } else {
                String labelIdText = labelId.getText();
                String text = txtTitle.getText();
                String authorText = txtAuthor.getText();
                String branchValue = comboBranch.getValue();
                String genreValue = comboGenre.getValue();
                String path = photoPath;
                BranchDto branchDto = getBranch(branchValue);
                boolean saved = bookBo.saveBook(new BookDto(labelIdText, new Branch(branchDto.getId(), branchDto.getName(), branchDto.getAddress(), branchDto.getContact(), branchDto.getEmail(), branchDto.getUsers(), branchDto.getBooks()), new ArrayList<>(), text, authorText, genreValue, path, true));
                if (saved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Can not Save").show();
                }

            }
        }


    }

    private boolean validateBook() {
        String name=txtTitle.getText();
        if(name.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Book title field empty").show();
            return false;
        }
        if(txtAuthor.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Author Name field empty").show();
            return false;
        }
        if(comboBranch.getValue() == null){
            new Alert(Alert.AlertType.ERROR,"Select a Branch").show();
            return false;
        }
        if(comboGenre.getValue() == null){
            new Alert(Alert.AlertType.ERROR,"Genre field empty").show();
            return false;
        }

        boolean matchName = Pattern.matches("^[A-Za-z]+(?:[ '-][A-Za-z]+)*$",name);
        if(!matchName){
            new Alert(Alert.AlertType.ERROR,"Invalid Book name").show();
            return  false;
        }
        boolean matchAuthor = Pattern.matches("^[A-Za-z]+(?:[ '-][A-Za-z]+)*$",txtAuthor.getText());
        if(!matchAuthor){
            new Alert(Alert.AlertType.ERROR,"Invalid Author name").show();
            return  false;
        }

        return true;
    }

    private boolean checkDuplicates() {
        List<BookDto> bookDtos = bookBo.loadAllBook();
        for (int i=0; i<bookDtos.size();i++){
            if(bookDtos.get(i).getId().equals(labelId.getText())){
                return true;
            }
        }
        return false;

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
    void addImageBtnOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            photoPath = selectedFile.getAbsolutePath();
        } else {
            photoPath=null;
        }
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
     /*   BookDto book = bookBo.getBook(labelId.getText());*/
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            BookDto book = bookBo.getBook(dto.getId());
            bookBo.deleteBook(book);
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully deleted").show();
        }

    }

    @FXML
    void comboGenreOnAction(ActionEvent event) {


    }

    public void comboBranchOnAction(ActionEvent actionEvent) {
        
    }
}

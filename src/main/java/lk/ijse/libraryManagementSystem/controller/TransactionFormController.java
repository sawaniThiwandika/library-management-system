package lk.ijse.libraryManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.bo.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.impl.BranchBoImpl;
import lk.ijse.libraryManagementSystem.bo.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;
import lk.ijse.libraryManagementSystem.dto.tm.TransactionTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionFormController {

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colIsReturn;

    @FXML
    private TableColumn<?, ?> colReservationDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;


    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private Label labelCount;

    @FXML
    private Label labelLate;

    @FXML
    private Label labelOngoing;

    @FXML
    private TableView<TransactionTm> tableTransaction;

    @FXML
    private ComboBox<String> comboBranch;
    BranchBo branchBo = new BranchBoImpl();
    TransactionBo transactionBo = new TransactionBoImpl();
    List<TransactionDto> allTransactions = new ArrayList<>();
    ObservableList<TransactionTm> tableValues = FXCollections.observableArrayList();

    public void initialize() {
        getLateReturns();
        setComboBoxValues();
        loadTransactions();
        setCellValueFactory();
        for (int i = 0; i < allTransactions.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(allTransactions.get(i).isReturn());
            tableValues.add(new TransactionTm(allTransactions.get(i).getId(), allTransactions.get(i).getReserveDate(),
                    allTransactions.get(i).getReturnDate(), allTransactions.get(i).getBook().getId(),
                    allTransactions.get(i).getBook().getTitle(), allTransactions.get(i).getUser().getEmail(), checkBox));

        }
        tableTransaction.setItems(tableValues);
        customiseFactory((TableColumn<TransactionTm, String>) colId);

    }

    private void getLateReturns() {
        List<TransactionDto> transactionDtos = transactionBo.lateReturns();
        for (int i=0;i<transactionDtos.size();i++){
            System.out.println("Id "+ transactionDtos.get(i).getId());
        }
    }

    private void customiseFactory(TableColumn<TransactionTm, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<TransactionTm, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<TransactionTm> currentRow = getTableRow();

                    if (!isEmpty()) {
                        boolean b = checkLate(currentRow);

                        if (!b)
                            currentRow.setStyle("-fx-background-color:lightcoral");

                    }
                }
            };
        });
    }

    public boolean checkLate(TableRow<TransactionTm> currentRow) {
        List<TransactionDto> transactionDtos = transactionBo.lateReturns();
        TransactionTm item = currentRow.getItem();
        /*if (item.getReturnDate().isBefore(LocalDate.now())) {
            if(!item.getIsReturn().isSelected()){
                return false;
            }

        }
        return true;*/
        for (int i=0;i<transactionDtos.size();i++){
            System.out.println("Id "+ transactionDtos.get(i).getId());
            if(item.getT_id().equals(transactionDtos.get(i).getId())){
                return false;
            }
        }
        return true;


    }

    private void setCellValueFactory() {

        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colId.setCellValueFactory(new PropertyValueFactory<>("t_id"));
        colIsReturn.setCellValueFactory(new PropertyValueFactory<>("isReturn"));

    }

    private void loadTransactions() {
        allTransactions = transactionBo.getAllTransactions();

    }

    private void setComboBoxValues() {

        ObservableList<String> types = FXCollections.observableArrayList();
        List<BranchDto> branchDtos = branchBo.loadAllBranch();
        types.add("All");
        for (BranchDto dto : branchDtos
        ) {
            for (int i = 0; i < types.size(); i++) {
                if (!dto.getName().equals(types.get(i))) {
                    types.add(dto.getName());
                }
            }
            if (types.size() == 1) {
                types.add(dto.getName());

            }

        }

        comboBranch.setItems(types);
    }

    public void comboBranchOnAction(ActionEvent actionEvent) {

        tableValues.clear();
        String value = comboBranch.getValue();
        for (int i = 0; i < allTransactions.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(allTransactions.get(i).isReturn());
            if (allTransactions.get(i).getBook().getBranch().getName().equals(value)) {
                tableValues.add(new TransactionTm(allTransactions.get(i).getId(), allTransactions.get(i).getReserveDate(), allTransactions.get(i).getReturnDate(),
                        allTransactions.get(i).getBook().getId(), allTransactions.get(i).getBook().getTitle(), allTransactions.get(i).getUser().getEmail(), checkBox));
            }
            if (value.equals("All")) {
                tableValues.add(new TransactionTm(allTransactions.get(i).getId(), allTransactions.get(i).getReserveDate(), allTransactions.get(i).getReturnDate(),
                        allTransactions.get(i).getBook().getId(), allTransactions.get(i).getBook().getTitle(), allTransactions.get(i).getUser().getEmail(), checkBox));
            }
        }
        tableTransaction.setItems(tableValues);
        customiseFactory((TableColumn<TransactionTm, String>) colId);
    }

}

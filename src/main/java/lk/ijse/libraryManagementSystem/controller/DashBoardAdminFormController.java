package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import lk.ijse.libraryManagementSystem.bo.custom.BookBo;
import lk.ijse.libraryManagementSystem.bo.custom.TransactionBo;
import lk.ijse.libraryManagementSystem.bo.custom.impl.BookBoImpl;
import lk.ijse.libraryManagementSystem.bo.custom.impl.TransactionBoImpl;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.TransactionDto;

import java.util.ArrayList;
import java.util.List;

public class DashBoardAdminFormController {

    @FXML
    private JFXButton addNewBook;

    @FXML
    private PieChart pieChart;
    String type;
    public void initialize() {
        TransactionBo transactionBo = new TransactionBoImpl();
        List<TransactionDto> allTransactions = transactionBo.getAllTransactions();
        BookBo bookBo=new BookBoImpl();
        List<BookDto> bookDtos = bookBo.loadAllBook();
        List<BookDto> available = new ArrayList<>();
        List<BookDto> ongoing = new ArrayList<>();
   /*     List<TransactionDto> late = new ArrayList<>();*/
        for(int i=0;i<bookDtos.size();i++){
            if(bookDtos.get(i).isAvailable()){
                available.add(bookDtos.get(i));
            }
            else{
                for(int j=0;j<allTransactions.size();j++){
                    if(allTransactions.get(i).isReturn()==false&&allTransactions.get(i).getBook().getId().equals(bookDtos.get(i).getId()))
                    {
                        ongoing.add(bookDtos.get(i));
                    }
                }
            }
        }
        List<TransactionDto> lateReturns = transactionBo.lateReturns();

        PieChart.Data slice1 = new PieChart.Data("Available", available.size());
        PieChart.Data slice2 = new PieChart.Data("Ongoing", ongoing.size());
        PieChart.Data slice3 = new PieChart.Data("Late",lateReturns.size() );

        pieChart.getData().addAll(slice1, slice2, slice3);
    }

}

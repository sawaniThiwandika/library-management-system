package lk.ijse.libraryManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class DashBoardAdminFormController {

    @FXML
    private JFXButton addNewBook;

    @FXML
    private PieChart pieChart;
    public void initialize() {
        // Initialize and set data for the pie chart
        PieChart.Data slice1 = new PieChart.Data("Available", 30);
        PieChart.Data slice2 = new PieChart.Data("Ongoing", 40);
        PieChart.Data slice3 = new PieChart.Data("Late", 30);

        pieChart.getData().addAll(slice1, slice2, slice3);
    }

}

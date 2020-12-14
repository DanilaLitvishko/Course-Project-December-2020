package sample.actionWindow.salesReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class SalesReport {
    @FXML
    PieChart pieChart;

    public void inizialize()
    {
        String answer = send("count,quantity");
        String[] text = answer.split("\n");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(String str:text)
        {
            String[] str2 = str.split(",");
            pieChartData.add(new PieChart.Data(str2[0], Integer.parseInt(str2[1])));
        }
        pieChart.setData(pieChartData);
    }
}

package sample.actionWindow.popularityReport;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import static sample.Controller.send;

public class PopularityReport {
    @FXML
    BarChart<String, Number> barChart;

    XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

    public void inizialize()
    {
        String answer = send("count,categories");
        dataSeries.getData().add((new XYChart.Data<String, Number>("Бытовая техника", Integer.parseInt(String.valueOf(answer.charAt(0))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Игрушки", Integer.parseInt(String.valueOf(answer.charAt(1))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Компьютерная техника", Integer.parseInt(String.valueOf(answer.charAt(2))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Косметика", Integer.parseInt(String.valueOf(answer.charAt(3))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Мебель", Integer.parseInt(String.valueOf(answer.charAt(4))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Продукты питания", Integer.parseInt(String.valueOf(answer.charAt(5))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Электроника", Integer.parseInt(String.valueOf(answer.charAt(6))))));
        barChart.getData().add(dataSeries);
    }
}

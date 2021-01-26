package sample.actionWindow.popularityReport;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static sample.Controller.send;

public class PopularityReport {
    @FXML
    BarChart<String, Number> barChart;

    XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show popularity chart");
        String entry = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject answer = (JSONObject)parser.parse(entry);
        dataSeries.getData().add((new XYChart.Data<String, Number>("Бытовая техника", Integer.parseInt(String.valueOf(answer.get("Бытовая техника"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Игрушки", Integer.parseInt(String.valueOf(answer.get("Игрушки"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Компьютерная техника", Integer.parseInt(String.valueOf(answer.get("Компьютерная техника"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Косметика", Integer.parseInt(String.valueOf(answer.get("Косметика"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Мебель", Integer.parseInt(String.valueOf(answer.get("Мебель"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Продукты питания", Integer.parseInt(String.valueOf(answer.get("Продукты питания"))))));
        dataSeries.getData().add((new XYChart.Data<String, Number>("Электроника", Integer.parseInt(String.valueOf(answer.get("Электроника"))))));
        barChart.getData().add(dataSeries);
    }
}

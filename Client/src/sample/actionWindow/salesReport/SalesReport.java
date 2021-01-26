package sample.actionWindow.salesReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.actionWindow.showOrders.Order;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class SalesReport {
    @FXML
    PieChart pieChart;

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show quantity chart");
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("products");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            pieChartData.add(new PieChart.Data(product.get("product").toString(), Integer.parseInt(product.get("quantity").toString())));
        }
        pieChart.setData(pieChartData);
    }
}

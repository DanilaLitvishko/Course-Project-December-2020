package sample.actionWindow.showProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static sample.Controller.send;

public class ShowProduct{
    @FXML
    TableView<Table> table;

    @FXML
    TableColumn<Table, String> number;

    @FXML
    TableColumn<Table, String> name;

    @FXML
    TableColumn<Table, String> date;

    @FXML
    TableColumn<Table, String> price;

    @FXML
    TableColumn<Table, String> category;

    ObservableList<Table> obList = FXCollections.observableArrayList();

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "product");
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("products");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            obList.add(new Table((String)product.get("name"), (String)product.get("date"), (String)product.get("price"), (String)product.get("category"), Integer.toString(i)));
        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.setItems(obList);
    }
}

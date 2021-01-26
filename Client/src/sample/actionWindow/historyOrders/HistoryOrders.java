package sample.actionWindow.historyOrders;

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

public class HistoryOrders {

    private String[] namesForAddToBasket = new String[20];

    private String login;

    private StringBuilder productId = new StringBuilder();

    @FXML
    TableView<Order> table;

    @FXML
    TableColumn<Order, String> product;

    @FXML
    TableColumn<Order, String> time;

    @FXML
    TableColumn<Order, String> quantity;

    @FXML
    TableColumn<Order, String> state;

    ObservableList<Order> obList = FXCollections.observableArrayList();

    public void inizialize(String userLogin) throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "category");
        request.put("login", userLogin);
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("users");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            obList.add(new Order((String)product.get("product"), (String)product.get("time"), (String)product.get("quantity"),
                    (String)product.get("state")));
        }
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        table.setItems(obList);
    }
}

package sample.actionWindow.showOrders;

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

public class ShowOrders {

    @FXML
    TableView<Order> table;

    @FXML
    TableColumn<Order, String> id;

    @FXML
    TableColumn<Order, String> userInfo;

    @FXML
    TableColumn<Order, String> email;

    @FXML
    TableColumn<Order, String> state;

    @FXML
    TableColumn<Order, String> basketId;

    ObservableList<Order> obList = FXCollections.observableArrayList();

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "category");
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("orders");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            obList.add(new Order((String)product.get("id"), (String)product.get("userInfo"), (String)product.get("email"),
                    (String)product.get("state"), (String)product.get("basketId")));
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userInfo.setCellValueFactory(new PropertyValueFactory<>("userInfo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        basketId.setCellValueFactory(new PropertyValueFactory<>("basketId"));
        table.setItems(obList);
    }
}

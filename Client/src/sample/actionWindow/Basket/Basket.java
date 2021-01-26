package sample.actionWindow.Basket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.actionWindow.createOrder.CreateOrder;

import java.io.IOException;

import static sample.Controller.send;

public class Basket {

    private String[] namesForAddToBasket = new String[20];

    private String login;

    private StringBuilder productId = new StringBuilder();

    @FXML
    TableView<BasketInfo> table;

    @FXML
    TableColumn<BasketInfo, String> product;

    @FXML
    TableColumn<BasketInfo, String> time;

    @FXML
    TableColumn<BasketInfo, String> quantity;

    ObservableList<BasketInfo> obList = FXCollections.observableArrayList();

    public void inizialize(String userLogin) throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "basket");
        request.put("login", userLogin);
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("basket");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            namesForAddToBasket[i] = (String)product.get("product");
            productId.append((String)product.get("id") + " ");
            obList.add(new BasketInfo((String)product.get("product"), (String)product.get("time"), (String)product.get("quantity")));
        }
        login = userLogin;
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(obList);
    }

    @FXML
    private void createOrder() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/createOrder/createOrder.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        CreateOrder controller = loader.getController();
        controller.inizialize(login, productId.toString());
        Stage stage = new Stage();
        stage.setTitle("Создание заказа");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }
}

package sample.actionWindow.showCategory;

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
import sample.actionWindow.showProduct.Table;
import sample.actionWindow.usersWindow.User;

import static sample.Controller.send;

public class ShowCategory {
    @FXML
    TableView<Category> table;

    @FXML
    TableColumn<Category, String> name;

    @FXML
    TableColumn<Category, String> description;


    ObservableList<Category> obList = FXCollections.observableArrayList();

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "category");
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("users");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            obList.add(new Category((String)product.get("name"), (String)product.get("description")));
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(obList);
    }
}

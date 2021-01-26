package sample.actionWindow.usersWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static sample.Controller.send;

public class UsersWindow {

    @FXML
    TableView<User> table;

    @FXML
    TableColumn<User, String> login;

    @FXML
    TableColumn<User, String> password;

    @FXML
    TableColumn<User, String> role;

    ObservableList<User> obList = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> userBox;

    @FXML
    ComboBox<String> categoryBox;

    @FXML
    TextField editText;

    public void inizialize() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("action", "show");
        request.put("object", "users");
        String answer = send(request.toJSONString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(answer);
        JSONArray products = (JSONArray) jsonObject.get("users");
        for(int i = 0;i < products.size();i++)
        {
            JSONObject product = (JSONObject)products.get(i);
            obList.add(new User((String)product.get("login"), (String)product.get("password"), (String)product.get("role")));
        }
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        table.setItems(obList);
    }

    @FXML
    private void delete() {
        String user = (String)userBox.getSelectionModel().getSelectedItem();
        if(user != null)
        {
            JSONObject request = new JSONObject();
            request.put("action", "delete");
            request.put("object", "product");
            request.put("user", user);
            String answer = send(request.toJSONString());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

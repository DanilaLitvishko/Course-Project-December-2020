package sample.actionWindow.editProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class EditProduct {

    private String[] namesForEdit = new String[10];

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

    @FXML
    ComboBox<String> numberBox;

    @FXML
    ComboBox<String> categoryBox;

    @FXML
    TextField editText;

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
            namesForEdit[i] = (String)product.get("name");
            numberBox.getItems().add(Integer.toString(i + 1));
            obList.add(new Table((String)product.get("name"), (String)product.get("date"), (String)product.get("price"), (String)product.get("category"), Integer.toString(i)));
        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.setItems(obList);
    }

    @FXML
    private void edit() {
        JSONObject request = new JSONObject();
        request.put("action", "edit");
        request.put("object", "product");

        String num = (String) numberBox.getSelectionModel().getSelectedItem();
        String check = (String) categoryBox.getSelectionModel().getSelectedItem();

        if(num != null || editText.getText().matches("[a-zA-Z]+\\s"))
        {
            if (check.equals("Цена")) {
                request.put("info", "price");
                request.put("old", namesForEdit[Integer.parseInt((String) numberBox.getSelectionModel().getSelectedItem())]);
                request.put("new", editText.getText());
                String answer = send(request.toJSONString());
            } else {
                if (check.equals("Название")) {
                    request.put("info", "name");
                    request.put("old", namesForEdit[Integer.parseInt((String) numberBox.getSelectionModel().getSelectedItem())]);
                    request.put("new", editText.getText());
                    String answer = send(request.toJSONString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Неправильный формат ввода!");
                    alert.showAndWait();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

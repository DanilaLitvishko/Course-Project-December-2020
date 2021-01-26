package sample.actionWindow.addProduct;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.json.simple.JSONObject;

import java.util.regex.Pattern;

import static sample.Controller.send;

public class AddProduct {
    @FXML
    javafx.scene.control.TextField name;

    @FXML
    javafx.scene.control.TextField price;

    @FXML
    ComboBox<String> category = new ComboBox<>();

    @FXML
    DatePicker date = new DatePicker();

    @FXML
    private void addProduct()
    {
        String str = (String)category.getSelectionModel().getSelectedItem();
        System.out.println(Pattern.matches(".*\\p{InCyrillic}.*", name.getText()));
        if(date.getValue() != null || str != null) {
            if (!Pattern.matches(".*\\p{InCyrillic}.*", name.getText()) || !price.getText().matches("[-+]?\\d+")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неправильный формат ввода!");
                alert.showAndWait();
            } else {
                JSONObject request = new JSONObject();
                request.put("action", "add");
                request.put("object", "product");
                request.put("name", name.getText());
                request.put("date", date.getValue().toString());
                request.put("price", price.getText());
                request.put("category", str);
                String answer = send(request.toJSONString());
                if (answer.equals("Error, this name in use")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Товар с таким названием уже существует!");
                    alert.showAndWait();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

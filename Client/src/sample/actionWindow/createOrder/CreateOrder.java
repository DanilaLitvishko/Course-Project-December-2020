package sample.actionWindow.createOrder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.json.simple.JSONObject;
import sample.Controller;

public class CreateOrder {
    private String login = new String();
    private String id = new String();
    @FXML
    private TextField info;
    @FXML
    private TextField email;

    public CreateOrder() {
    }

    @FXML
    private void sendOrder() {
        JSONObject request = new JSONObject();
        if (this.info.getText().matches("[a-zA-Z]+\\s") && this.email.getText().matches("[a-zA-Z]+")) {
            String var10000 = this.id;
            Controller.send("createOrder," + var10000 + "," + this.info.getText() + "," + this.email.getText());
            request.put("action", "createOrder");
            request.put("id", this.id);
            request.put("email", this.email.getText());
            request.put("user info", this.info.getText());
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }

    }

    public void inizialize(String userLogin, String productId) {
        this.login = userLogin;
        this.id = productId;
    }
}
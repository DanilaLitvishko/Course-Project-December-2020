package sample.actionWindow.createOrder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Controller.send;

public class CreateOrder {

    private String login = new String();
    private String id = new String();

    @FXML
    private TextField info;

    @FXML
    private TextField email;

    @FXML
    private void sendOrder()
    {
        if(!info.getText().matches("[a-zA-Z]+\\s") || !email.getText().matches("[a-zA-Z]+"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
        else {
            String answer = send("createOrder," + id + "," + info.getText() + "," + email.getText());
        }
    }

    public void inizialize(String userLogin, String productId)
    {
        login = userLogin;
        id = productId;
    }
}

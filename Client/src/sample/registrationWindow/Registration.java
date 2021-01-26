package sample.registrationWindow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.Controller;

import static sample.Controller.send;

public class Registration {

    @FXML
    Button registrationButton = new Button();

    @FXML
    ComboBox<String> roles = new ComboBox<>();

    @FXML
    private TextField registrationLogin;

    @FXML
    private PasswordField registrationPassword;

    @FXML
    private void registration()
    {
        if(!registrationLogin.getText().matches("[a-zA-Z]+"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильно введен логин!");
            alert.showAndWait();
        }
        else{
            JSONObject request = new JSONObject();
            request.put("action", "registration");
            request.put("login", registrationLogin.getText());
            request.put("password", registrationPassword.getText());
            request.put("role", (String)roles.getSelectionModel().getSelectedItem());
            String answer = send(request.toJSONString());
            if(answer.equals("Error!"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неправильно введен логин или пароль!");
                alert.showAndWait();
            }
            else{
                if(answer.equals("This user is"))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Пользователь с таким логином уже существует!");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Оповещение");
                    alert.setHeaderText("Регистрация прошла успешно!");
                    alert.showAndWait();
                }
            }
            Stage stage = (Stage) registrationButton.getScene().getWindow();
            stage.close();
        }
    }
}

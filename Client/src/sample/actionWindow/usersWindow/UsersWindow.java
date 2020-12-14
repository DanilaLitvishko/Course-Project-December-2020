package sample.actionWindow.usersWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.actionWindow.showProduct.Table;

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

    public void inizialize()
    {
        String answer = send("show,users");
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            obList.add(new User(str2[0], str2[1], str2[2]));
            userBox.getItems().add(str2[0]);
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
            String answer = send("delete user " + (String) userBox.getSelectionModel().getSelectedItem());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

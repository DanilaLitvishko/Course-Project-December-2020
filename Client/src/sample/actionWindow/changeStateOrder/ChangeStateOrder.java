package sample.actionWindow.changeStateOrder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.actionWindow.showOrders.Order;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class ChangeStateOrder {

    private String[] namesForEdit = new String[10];

    @FXML
    ComboBox<String> idBox;

    @FXML
    ComboBox<String> stateBox;

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

    public void inizialize()
    {
        String answer = send("show,orders");
        String[] text = answer.split("\n");
        for(String str:text)
        {
            String[] str2 = str.split(",");
            obList.add(new Order(str2[0], str2[1], str2[2], str2[3], str2[4]));
            idBox.getItems().add(str2[0]);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userInfo.setCellValueFactory(new PropertyValueFactory<>("userInfo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        basketId.setCellValueFactory(new PropertyValueFactory<>("basketId"));
        table.setItems(obList);
    }

    @FXML
    private void edit() {
        String check = (String) idBox.getSelectionModel().getSelectedItem();
        String state = (String) stateBox.getSelectionModel().getSelectedItem();
        if(check != null || state != null)
        {
            String answer = send("edit state " + check + " " + state);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

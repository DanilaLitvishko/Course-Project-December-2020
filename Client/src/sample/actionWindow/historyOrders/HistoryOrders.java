package sample.actionWindow.historyOrders;

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
import sample.actionWindow.Basket.BasketInfo;
import sample.actionWindow.createOrder.CreateOrder;

import java.io.IOException;

import static sample.Controller.send;

public class HistoryOrders {

    private String[] namesForAddToBasket = new String[20];

    private String login;

    private StringBuilder productId = new StringBuilder();

    @FXML
    TableView<Order> table;

    @FXML
    TableColumn<Order, String> product;

    @FXML
    TableColumn<Order, String> time;

    @FXML
    TableColumn<Order, String> quantity;

    @FXML
    TableColumn<Order, String> state;

    ObservableList<Order> obList = FXCollections.observableArrayList();

    public void inizialize(String userLogin)
    {
        String answer = send("show,order," + userLogin);
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            obList.add(new Order(str2[0], str2[1], str2[2], str2[3]));

        }
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        table.setItems(obList);
    }
}

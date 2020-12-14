package sample.actionWindow.Basket;

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
import sample.actionWindow.createOrder.CreateOrder;

import java.io.IOException;

import static sample.Controller.send;

public class Basket {

    private String[] namesForAddToBasket = new String[20];

    private String login;

    private StringBuilder productId = new StringBuilder();

    @FXML
    TableView<BasketInfo> table;

    @FXML
    TableColumn<BasketInfo, String> product;

    @FXML
    TableColumn<BasketInfo, String> time;

    @FXML
    TableColumn<BasketInfo, String> quantity;

    ObservableList<BasketInfo> obList = FXCollections.observableArrayList();

    public void inizialize(String userLogin)
    {
        String answer = send("show,basket," + userLogin);
        login = userLogin;
        System.out.println(answer);
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            namesForAddToBasket[i] = str2[0];
            productId.append(str2[3] + " ");
            obList.add(new BasketInfo(str2[0], str2[1] , str2[2]));

        }
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(obList);
    }

    @FXML
    private void createOrder() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/createOrder/createOrder.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        CreateOrder controller = loader.getController();
        controller.inizialize(login, productId.toString());
        Stage stage = new Stage();
        stage.setTitle("Создание заказа");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }
}

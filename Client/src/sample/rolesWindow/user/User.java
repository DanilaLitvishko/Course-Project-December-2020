package sample.rolesWindow.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import sample.actionWindow.Basket.Basket;
import sample.actionWindow.Basket.BasketInfo;
import sample.actionWindow.addToBasket.AddToBasket;
import sample.actionWindow.createOrder.CreateOrder;
import sample.actionWindow.historyOrders.HistoryOrders;
import sample.actionWindow.showCategory.ShowCategory;
import sample.actionWindow.showProduct.ShowProduct;

import java.io.IOException;

import static sample.Controller.send;

public class User {

    private String login = new String();

    @FXML
    private void openShowWindow() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/showProduct/showProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        ShowProduct controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Таблица товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openShowCategoryWindow() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/showCategory/showCategory.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        ShowCategory controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Таблица категорий товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void addToBasket()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/addToBasket/addToBasket.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        AddToBasket controller = loader.getController();
        controller.inizialize(login);
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Таблица категорий товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void showBasket() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/Basket/basket.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Basket controller = loader.getController();
        controller.inizialize(login);
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Таблица категорий товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void createOrder()
    {
        String answer = send("show,basket," + login);
        StringBuffer productId = new StringBuffer();
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            productId.append(str2[3] + " ");
        }
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

    @FXML
    private void historyOrders() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/historyOrders/historyOrders.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        HistoryOrders controller = loader.getController();
        controller.inizialize(login);
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Таблица категорий товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    public void inizialize(String userLogin)
    {
        login = userLogin;
    }
}

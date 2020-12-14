package sample.rolesWindow.provider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Provider {
    @FXML
    private void openShowWindow()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/showProduct/showProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openAddWindow()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/addProduct/addProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openDeleteWindow()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/deleteProduct/deleteProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openEditWindow()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/editProduct/editProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openPopularityReport()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/popularityReport/popularityReport.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openSalesReport()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/salesReport/salesReport.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }
}

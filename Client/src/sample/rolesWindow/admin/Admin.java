package sample.rolesWindow.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import sample.actionWindow.changeStateOrder.ChangeStateOrder;
import sample.actionWindow.deleteProduct.DeleteProduct;
import sample.actionWindow.editProduct.EditProduct;
import sample.actionWindow.popularityReport.PopularityReport;
import sample.actionWindow.salesReport.SalesReport;
import sample.actionWindow.showOrders.ShowOrders;
import sample.actionWindow.showProduct.ShowProduct;
import sample.actionWindow.usersWindow.UsersWindow;

import java.io.IOException;

public class Admin {

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
        stage.setTitle("Окно добавления товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openDeleteWindow() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/deleteProduct/deleteProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        DeleteProduct controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно удаления товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openEditWindow() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/editProduct/editProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        EditProduct controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно редактирования товаров");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openPopularityReport() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/popularityReport/popularityReport.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        PopularityReport controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно отчета о популярности");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openSalesReport() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/salesReport/salesReport.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        SalesReport controller = new SalesReport();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно отчета о продажах");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void openUsersWindow() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/usersWindow/usersWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        UsersWindow controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно работы с пользователями");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void showOrders() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/showOrders/showOrders.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        ShowOrders controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно работы с пользователями");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }

    @FXML
    private void changeStateOrder() throws ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actionWindow/changeStateOrder/changeStateOrder.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        ChangeStateOrder controller = loader.getController();
        controller.inizialize();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.setTitle("Окно работы с пользователями");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }
}

package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.animation.Shake;
import sample.rolesWindow.user.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

    @FXML
    private TextField authorizationLogin;

    @FXML
    private PasswordField authorizationPassword;

    @FXML
    private Button authorizationButton = new Button();

    @FXML
    private void authorization()
    {
        //Добавить анимацию движения полей, если авторизация не успешна

        String result = send("authorization," + authorizationLogin.getText() + "," + authorizationPassword.getText());
        if(result.equals("Not found"))
        {
            Shake userPassw = new Shake(authorizationPassword);
            Shake userLog = new Shake(authorizationLogin);
            userLog.playAnimation();
            userPassw.playAnimation();
        }
        else {
            Stage stage = (Stage) authorizationButton.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Оповещение");
            alert.setHeaderText("Авторизация прошла успешно!");
            alert.showAndWait();
            switch (result)
            {
                case "Пользователь":
                    FXMLLoader loader1 = new FXMLLoader();
                    loader1.setLocation(getClass().getResource("/sample/rolesWindow/user/user.fxml"));
                    try {
                        loader1.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root1 = loader1.getRoot();
                    User controller = loader1.getController();
                    controller.inizialize(authorizationLogin.getText());
                    Stage stage1 = new Stage();
                    //Добавить анимацию открытия нового окна
                    stage1.getIcons().add(new Image(getClass().getResourceAsStream("/sample/images/reg.jpg")));
                    stage1.setTitle("Меню пользователя");
                    stage1.setScene(new Scene(root1 , 400, 400));
                    stage1.showAndWait();
                    break;
                case "Администратор":
                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("/sample/rolesWindow/admin/admin.fxml"));
                    try {
                        loader2.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root2 = loader2.getRoot();
                    Stage stage2 = new Stage();
                    //Добавить анимацию открытия нового окна
                    stage2.getIcons().add(new Image(getClass().getResourceAsStream("/sample/images/reg.jpg")));
                    stage2.setTitle("Меню Администратора");
                    stage2.setScene(new Scene(root2, 400, 400));
                    stage2.showAndWait();
                    break;
                case "Поставщик":
                    FXMLLoader loader3 = new FXMLLoader();
                    loader3.setLocation(getClass().getResource("/sample/rolesWindow/provider/provider.fxml"));
                    try {
                        loader3.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root3 = loader3.getRoot();
                    Stage stage3 = new Stage();
                    //Добавить анимацию открытия нового окна
                    stage3.getIcons().add(new Image(getClass().getResourceAsStream("/sample/images/reg.jpg")));
                    stage3.setTitle("Меню поставщика");
                    stage3.setScene(new Scene(root3 , 400, 400));
                    stage3.showAndWait();
                    break;
            }
        }

    }

    public static String send(String sendStr)
    {
        String answer;
        try(Socket socket = new Socket("localhost", 2525);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); )
        {
                oos.writeObject(sendStr);
                answer = (String)ois.readObject();
                return answer;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void registration()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/registrationWindow/registration.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Добавить анимацию открытия нового окна
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/sample/images/reg.jpg")));
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root , 400, 400));
        stage.showAndWait();
    }
}

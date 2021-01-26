package sample.actionWindow.addToBasket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONObject;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class AddToBasket {

    private String[] namesForAddToBasket = new String[20];

    private String login;

    @FXML
    TableView<Table> table;

    @FXML
    TableColumn<Table, String> number;

    @FXML
    TableColumn<Table, String> name;

    @FXML
    TableColumn<Table, String> date;

    @FXML
    TableColumn<Table, String> price;

    @FXML
    TableColumn<Table, String> category;

    ObservableList<Table> obList = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> numberBox;

    @FXML
    ComboBox<String> quantityBox;

    public void inizialize(String userLogin)
    {
        login = userLogin;
        String answer = send("show,product");
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            namesForAddToBasket[i] = str2[0];
            obList.add(new Table(str2[0], str2[1], str2[2], str2[3], Integer.toString(i)));
        }
        for(int j=0;j<i;j++)
        {
            numberBox.getItems().add(Integer.toString(j + 1));
        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.setItems(obList);
    }

    @FXML
    private void addToBasket() {
        String str1 = (String) numberBox.getSelectionModel().getSelectedItem();
        String str2 = (String) quantityBox.getSelectionModel().getSelectedItem();
        if(str1 != null || str2 != null)
        {
            JSONObject request = new JSONObject();
            request.put("action", "add");
            request.put("object", "toBasket");
            request.put("name", namesForAddToBasket[Integer.parseInt(str1)]);
            request.put("login", login);
            request.put("quantity", Integer.parseInt(str2));
            String answer = send(request.toJSONString());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

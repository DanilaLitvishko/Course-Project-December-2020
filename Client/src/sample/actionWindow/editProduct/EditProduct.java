package sample.actionWindow.editProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class EditProduct {

    private String[] namesForEdit = new String[10];

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
    ComboBox<String> categoryBox;

    @FXML
    TextField editText;

    public void inizialize()
    {
        String answer = send("show,product");
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            namesForEdit[i] = str2[0];
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
    private void edit() {
        String num = (String) numberBox.getSelectionModel().getSelectedItem();
        String check = (String) categoryBox.getSelectionModel().getSelectedItem();
        if(num != null || editText.getText().matches("[a-zA-Z]+\\s"))
        {
            if (check.equals("Цена")) {
                String info = "price";
                String answer = send("edit product " +
                        namesForEdit[Integer.parseInt((String) numberBox.getSelectionModel().getSelectedItem())] + " " +
                        info + " " + editText.getText());
            } else {
                if (check.equals("Название")) {
                    String info = "name";
                    String answer = send("edit product " +
                            namesForEdit[Integer.parseInt((String) numberBox.getSelectionModel().getSelectedItem())] + " " +
                            info + " " + editText.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Неправильный формат ввода!");
                    alert.showAndWait();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный формат ввода!");
            alert.showAndWait();
        }
    }
}

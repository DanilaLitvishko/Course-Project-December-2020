package sample.actionWindow.showProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static sample.Controller.send;

public class ShowProduct{
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

    public void inizialize()
    {
        String answer = send("show,product");
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            obList.add(new Table(str2[0], str2[1], str2[2], str2[3], Integer.toString(i)));
        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.setItems(obList);
    }
}

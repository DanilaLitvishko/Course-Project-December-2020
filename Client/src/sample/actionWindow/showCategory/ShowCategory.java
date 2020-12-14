package sample.actionWindow.showCategory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class ShowCategory {
    @FXML
    TableView<Category> table;

    @FXML
    TableColumn<Category, String> name;

    @FXML
    TableColumn<Category, String> description;


    ObservableList<Category> obList = FXCollections.observableArrayList();

    public void inizialize()
    {
        String answer = send("show,category");
        String[] text = answer.split("\n");
        int i = 0;
        for(String str:text)
        {
            i++;
            String[] str2 = str.split(",");
            obList.add(new Category(str2[0], str2[1]));
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(obList);
    }
}

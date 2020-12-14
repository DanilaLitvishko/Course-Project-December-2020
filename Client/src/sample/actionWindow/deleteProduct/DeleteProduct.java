package sample.actionWindow.deleteProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.actionWindow.showProduct.Table;

import static sample.Controller.send;

public class DeleteProduct {

        private String[] namesForDelete = new String[10];

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

        public void inizialize()
        {
            String answer = send("show,product");
            String[] text = answer.split("\n");
            int i = 0;
            for(String str:text)
            {
                i++;
                String[] str2 = str.split(",");
                namesForDelete[i] = str2[0];
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
        private void delete() {
            String str = (String) numberBox.getSelectionModel().getSelectedItem();
            if(str != null)
            {
                String answer = send("delete,product," + namesForDelete[Integer.parseInt((String) numberBox.getSelectionModel().getSelectedItem())]);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неправильный формат ввода!");
                alert.showAndWait();
            }
        }
}

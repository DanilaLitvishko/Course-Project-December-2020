package ForChart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ForPopularityChart {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public ForPopularityChart (JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void send() throws SQLException, IOException {
        JSONObject categories = new JSONObject();

        String query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Бытовая техника'";
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Бытовая техника", resultSet.getString(1));
        }
        else
        {
            categories.put("Бытовая техника", 0);
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Игрушки'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Игрушки", resultSet.getString(1));
        }
        else
        {
            categories.put("Игрушки", resultSet.getString(0));
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Компьютерная техника'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Компьютерная техника", resultSet.getString(1));
        }
        else
        {
            categories.put("Компьютерная техника", 0);
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Косметика'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Косметика", resultSet.getString(1));
        }
        else
        {
            categories.put("Косметика", resultSet.getString(0));
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Мебель'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Мебель", resultSet.getString(1));
        }
        else
        {
            categories.put("Мебель", 0);
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Продукты питания'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Продукты питания", resultSet.getString(1));
        }
        else
        {
            categories.put("Продукты питания", 0);
        }
        query = "SELECT count(product.category) FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n"
                + "WHERE category='Электроника'";
        resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            categories.put("Электроника", resultSet.getString(1));
        }
        else
        {
            categories.put("Электроника", 0);
        }
        out.writeObject(categories.toJSONString());
    }
}

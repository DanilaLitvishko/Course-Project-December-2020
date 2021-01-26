package Actions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Show {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public Show(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void show() throws SQLException, IOException {
        String query;
        ResultSet resultSet;
        JSONArray jsonArray = new JSONArray();
        JSONObject products = new JSONObject();
        switch ((String)request.get("object"))
        {
            case "product":
                query = "SELECT * FROM product";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", resultSet.getString(1));
                    jsonObject.put("date", resultSet.getString(2));
                    jsonObject.put("price", resultSet.getString(3));
                    jsonObject.put("category", resultSet.getString(4));
                    jsonArray.add(jsonObject);
                }
                products.put("products", jsonArray);
                out.writeObject(products.toJSONString());
                break;
            case "basket":
                query = "SELECT product, time, quantity, id FROM basket WHERE user='" + request.get("login") + "'";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("product", resultSet.getString(1));
                    jsonObject.put("time", resultSet.getString(2));
                    jsonObject.put("quantity", resultSet.getString(3));
                    jsonObject.put("id", resultSet.getString(4));
                    jsonArray.add(jsonObject);
                }
                products.put("basket", jsonArray);
                out.writeObject(products.toJSONString());
                break;
            case "users":
                query = "SELECT * FROM users";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("login", resultSet.getString(1));
                    jsonObject.put("password", resultSet.getString(2));
                    jsonObject.put("role", resultSet.getString(3));
                    jsonArray.add(jsonObject);
                }
                products.put("users", jsonArray);
                out.writeObject(products.toJSONString());
                break;
            case "category":
                query = "SELECT * FROM category";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", resultSet.getString(1));
                    jsonObject.put("description", resultSet.getString(2));
                    jsonArray.add(jsonObject);
                }
                products.put("categories", jsonArray);
                out.writeObject(products.toJSONString());
                break;
            case "order":
                query = "SELECT * FROM booked";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("product", resultSet.getString(1));
                    jsonObject.put("time", resultSet.getString(2));
                    jsonObject.put("quantity", resultSet.getString(3));
                    jsonObject.put("state", resultSet.getString(4));
                    jsonArray.add(jsonObject);
                }
                products.put("orders", jsonArray);
                out.writeObject(products.toJSONString());
                break;
            case "orders":
                query = "SELECT * FROM booked";
                resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", resultSet.getString(1));
                    jsonObject.put("userInfo", resultSet.getString(2));
                    jsonObject.put("email", resultSet.getString(3));
                    jsonObject.put("state", resultSet.getString(4));
                    jsonObject.put("basketId", resultSet.getString(5));
                    jsonArray.add(jsonObject);
                }
                products.put("orders", jsonArray);
                out.writeObject(products.toJSONString());
                break;
        }
    }
}

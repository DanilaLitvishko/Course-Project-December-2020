package Actions;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Add {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public Add(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void add() throws SQLException, IOException {
        String query;
        ResultSet resultSet;
        switch ((String)request.get("object"))
        {
            case "product":
                query = "SELECT name FROM product WHERE name='" + request.get("name") + "'";
                resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    out.writeObject("Error, this name in use");
                }
                else
                {
                    query = "INSERT INTO product(name, date, price, category) VALUES ('" + request.get("name") + "', '" + request.get("date") +
                            "', '" + request.get("price") + "', '" + request.get("category") + "')";
                    statement.executeUpdate(query);
                    out.writeObject("Good!");
                }
                break;
            case "toBasket":
                query = "SELECT product FROM basket WHERE product='" + request.get("name") + "'";
                resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    out.writeObject("Error, this name in use");
                }
                else
                {
                    Date date = new Date();
                    query = "INSERT INTO basket(product, user, quantity, time) VALUES ('"
                            + request.get("name") + "', '" + request.get("login") + "', '" + request.get("quantity") + "', '" + date.toString() + "')";
                    statement.executeUpdate(query);
                    out.writeObject("Good!");
                }
                break;
        }
    }
}

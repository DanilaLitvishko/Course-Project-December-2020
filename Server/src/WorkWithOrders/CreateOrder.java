package WorkWithOrders;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateOrder {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public CreateOrder(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void createOrder() throws SQLException, IOException {
        String[] id  = request.get("id").toString().split(" ");
        for(String i : id)
        {
            String query = "INSERT INTO booked(userInfo, email, state, basket_id) VALUES ('"
                    + request.get("user info") + "', '" + request.get("email") + "', 'Подготавливается', '"+ Integer.parseInt(i) + "')";
            statement.executeUpdate(query);
        }
        out.writeObject("Good!");
    }
}

package Actions;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public Delete(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void delete() throws SQLException, IOException {
        String query;
        switch ((String)request.get("object")) {
            case "product":
                query = "DELETE FROM product WHERE name='" + request.get("nameProduct") + "'";
                statement.executeUpdate(query);
                out.writeObject("Good!");
                break;
            case "user":
                query = "DELETE FROM users WHERE login='" + request.get("user") + "'";
                statement.executeUpdate(query);
                out.writeObject("Good!");
                break;
        }
    }
}

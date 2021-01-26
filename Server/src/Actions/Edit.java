package Actions;

import org.json.simple.JSONObject;

import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public Edit(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void edit() throws SQLException {
        String query;
        switch ((String)request.get("info")) {
            case "price":
                query = "UPDATE product SET " + request.get("info") + "='" + request.get("new") + "' WHERE " + request.get("info") + "='" + request.get("old") + "'";
                statement.executeUpdate(query);
                break;
            case "name":
                query = "UPDATE product SET " + request.get("info") + "='" + request.get("new") + "' WHERE " + request.get("info") + "='" + request.get("old") + "'";
                statement.executeUpdate(query);
                break;
            case "state":
                query = "UPDATE product SET " + request.get("info") + "='" + request.get("new") + "' WHERE id='" + request.get("id") + "'";
                statement.executeUpdate(query);
                break;
        }
    }
}

package Entrance;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorization {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public Authorization(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void authorization() throws SQLException, IOException {

        if(request.get("login") != null && request.get("password") != null) {
            String query = "SELECT roles FROM users WHERE login='" + request.get("login") + "' " + "AND passw='" + request.get("password") + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                out.writeObject("Not found");
            } else {
                out.writeObject(resultSet.getString(1));
            }
        }
        else
        {
            out.writeObject("Error!");
        }
    }
}

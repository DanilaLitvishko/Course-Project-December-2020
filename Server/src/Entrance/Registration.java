package Entrance;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registration {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;
    public Registration(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void registration() throws SQLException, IOException {
        if(request.get("login") != null && request.get("password") != null && request.get("role") != null) {
            String query = "SELECT * FROM users WHERE login='" + request.get("login") + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                query = "INSERT INTO users(login, passw, roles)  VALUES ('"
                        + request.get("login") + "', '" + request.get("password") + "', '" + request.get("role") + "')";
                statement.executeUpdate(query);
                out.writeObject("Good");
            } else {
                out.writeObject("This user is");
            }
        }
        else
        {
            out.writeObject("Error!");
        }
    }
}
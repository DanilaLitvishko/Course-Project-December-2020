package ForChart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ForQuantityChart {
    JSONObject request;
    Statement statement;
    ObjectOutputStream out;

    public ForQuantityChart(JSONObject request, Statement statement, ObjectOutputStream out)
    {
        this.request = request;
        this.statement = statement;
        this.out = out;
    }

    public void send() throws SQLException, IOException {
        String query = "SELECT product, quantity FROM booked\n"
                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                + "INNER JOIN product ON product.name=basket.product\n";
        ResultSet resultSet = statement.executeQuery(query);
        JSONArray jsonArray = new JSONArray();
        JSONObject products = new JSONObject();
        StringBuffer sb = new StringBuffer();
        while (resultSet.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product", resultSet.getString(1));
            jsonObject.put("quantity", resultSet.getString(2));
            jsonArray.add(jsonObject);
        }
        products.put("products", jsonArray);
        out.writeObject(products.toJSONString());
        out.writeObject(sb.toString());
    }
}

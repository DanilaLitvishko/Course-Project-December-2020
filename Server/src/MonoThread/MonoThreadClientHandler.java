package MonoThread;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.Date;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    private String url = "jdbc:mysql://localhost/users";
    private String user = "root";
    private String password = "xren3001";

    private Statement statement;
    private Connection conn;

    public MonoThreadClientHandler(Socket client) throws SQLException {
        MonoThreadClientHandler.clientDialog = client;
        conn = DriverManager.getConnection(url, user, password);
        statement = conn.createStatement();
    }

    @Override
    public void run() {

        try {
            // инициируем каналы общения в сокете, для сервера

            // канал записи в сокет следует инициализировать сначала канал чтения для избежания блокировки выполнения программы на ожидании заголовка в сокете
            ObjectOutputStream out = new ObjectOutputStream(clientDialog.getOutputStream());

            // канал чтения из сокета
            ObjectInputStream in = new ObjectInputStream(clientDialog.getInputStream());
            // основная рабочая часть
            // начинаем диалог с подключенным клиентом в цикле, пока сокет не
            // закрыт клиентом


            if (!clientDialog.isClosed()) {
                String entry;
                entry = (String)in.readObject();
                String[] answer = entry.split(",");
                if(answer[0].equals("registration"))
                {
                    if(answer.length < 3)
                    {
                        out.writeObject("Error");
                    }
                    else {
                        String query = "SELECT * FROM users WHERE login='" + answer[1] + "'";
                        ResultSet resultSet = statement.executeQuery(query);
                        if (!resultSet.next()) {
                            query = "INSERT INTO users(login, passw, roles)  VALUES ('"
                                    + answer[1] + "', '" + answer[2] + "', '" + answer[3] + "')";
                            statement.executeUpdate(query);
                            out.writeObject("Good");
                        } else {
                            out.writeObject("This user is");
                        }
                    }
                }
                if(answer[0].equals("authorization"))
                {
                    if(answer.length < 3)
                    {
                        out.writeObject("Not found");
                    }
                    else {
                        String query = "SELECT roles FROM users WHERE login='" + answer[1] + "' " + "AND passw='" + answer[2] + "'";
                        ResultSet resultSet = statement.executeQuery(query);
                        if (!resultSet.next()) {
                            out.writeObject("Not found");
                        } else {
                            out.writeObject(resultSet.getString(1));
                        }
                    }
                }
                if(answer[0].equals("addProduct"))
                {
                    String query = "SELECT name FROM product WHERE name='" + answer[1] + "'";
                    ResultSet resultSet = statement.executeQuery(query);
                    if(resultSet.next())
                    {
                        out.writeObject("Error, this name in use");
                    }
                    else
                    {
                        StringBuffer query2 = new StringBuffer();
                        query = "INSERT INTO product(name, date, price, category) VALUES ('" + answer[1] + "', '" + answer[2] +
                                "', '" + answer[3] + "', '" + answer[4] + "')";
                        statement.executeUpdate(query);
                        out.writeObject("Good!");
                    }
                }
                if(answer[0].equals("addToBasket"))
                {
                    String query = "SELECT product FROM basket WHERE product='" + answer[1] + "'";
                    ResultSet resultSet = statement.executeQuery(query);
                    if(resultSet.next())
                    {
                        out.writeObject("Error, this name in use");
                    }
                    else
                    {
                        Date date = new Date();
                        query = "INSERT INTO basket(product, user, quantity, time) VALUES ('"
                                + answer[1] + "', '" + answer[2] + "', '" + Integer.parseInt(answer[3]) + "', '" + date.toString() + "')";
                        System.out.println(query);
                        statement.executeUpdate(query);
                        out.writeObject("Good!");
                    }
                }
                if(answer[0].equals("createOrder"))
                {
                    String[] id  = answer[1].split(" ");
                    for(String i : id)
                    {
                        String query = "INSERT INTO booked(userInfo, email, state, basket_id) VALUES ('"
                                + answer[2] + "', '" + answer[3] + "', 'Подготавливается', '"+ Integer.parseInt(i) + "')";
                        statement.executeUpdate(query);
                    }
                    out.writeObject("Good!");
                }
                if (answer[0].equals("show"))
                {
                    if(answer[1].equals("product"))
                    {
                        String query = "SELECT * FROM product";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) +
                                    "," + resultSet.getString(3) + "," + resultSet.getString(4) + "\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                    if(answer[1].equals("users"))
                    {
                        String query = "SELECT * FROM users";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) +
                                    "," + resultSet.getString(3) + "\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                    if (answer[1].equals("category"))
                    {
                        String query = "SELECT * FROM category";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) + "\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                    if(answer[1].equals("basket"))
                    {
                        String query = "SELECT product, time, quantity, id FROM basket WHERE user='" + answer[2] + "'";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) +
                                    "," + resultSet.getString(3) + "," + resultSet.getString(4) + "\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                    if (answer[1].equals("order"))
                    {
                        String query = "SELECT basket.product, basket.time, basket.quantity, booked.state FROM booked\n" +
                                "INNER JOIN basket ON basket.id=booked.basket_id\n" +
                                "WHERE user='" + answer[2] + "'";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) +
                                    "," + resultSet.getString(3) + "," + resultSet.getString(4) + "\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                    if(answer[1].equals("orders"))
                    {
                        String query = "SELECT * FROM booked" ;
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) +
                                    "," + resultSet.getString(3) + "," + resultSet.getString(4) +
                                    "," + resultSet.getString(5) +"\n");
                        }
                        String str = sb.toString();
                        out.writeObject(str);
                    }
                }
                if(answer[0].equals("delete"))
                {
                    if(answer[1].equals("product"))
                    {
                        String query = "DELETE FROM product WHERE name='" + answer[2] + "'";
                        statement.executeUpdate(query);
                        out.writeObject("Good!");
                    }
                    if(answer[1].equals("user"))
                    {
                        String query = "DELETE FROM users WHERE login='" + answer[2] + "'";
                        statement.executeUpdate(query);
                        out.writeObject("Good!");
                    }
                }
                if(answer[0].equals("edit"))
                {
                    if(answer[1].equals("product"))
                    {
                        if(answer[4].equals("price"))
                        {
                            String query = "UPDATE product SET " + answer[3] + "='" + Integer.parseInt(answer[4]) + "' WHERE " + answer[3] + "='" + answer[2] + "'";
                            statement.executeUpdate(query);
                        }
                        else {
                            String query = "UPDATE product SET " + answer[3] + "='" + answer[4] + "' WHERE " + answer[3] + "='" + answer[2] + "'";
                            statement.executeUpdate(query);
                        }
                        out.writeObject("Good!");
                    }
                    if(answer[1].equals("state"))
                    {
                        String query = "UPDATE booked SET state='" + answer[3] + "' WHERE id='" + answer[2] + "'";
                        statement.executeUpdate(query);
                    }
                }
                if(answer[0].equals("count"))
                {
                    if(answer[1].equals("categories"))
                    {
                        StringBuffer sb = new StringBuffer();
                        String query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Бытовая техника'";
                        ResultSet resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Игрушки'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Компьютерная техника'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Косметика'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Мебель'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Продукты питания'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        query = "SELECT count(product.category) FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n"
                                + "WHERE category='Электроника'";
                        resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            sb.append(resultSet.getString(1));
                        }
                        else
                        {
                            sb.append("0,");
                        }
                        out.writeObject(sb.toString());
                    }
                    if(answer[1].equals("quantity"))
                    {
                        String query = "SELECT product, quantity FROM booked\n"
                                + "INNER JOIN basket ON basket.id=booked.basket_id\n"
                                + "INNER JOIN product ON product.name=basket.product\n";
                        ResultSet resultSet = statement.executeQuery(query);
                        StringBuffer sb = new StringBuffer();
                        while (resultSet.next())
                        {
                            sb.append(resultSet.getString(1) + "," + resultSet.getString(2) + "\n");
                        }
                        out.writeObject(sb.toString());
                    }
                }
            }
            in.close();
            out.close();
            clientDialog.close();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
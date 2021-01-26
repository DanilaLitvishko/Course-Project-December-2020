package MonoThread;

import Actions.Add;
import Actions.Delete;
import Actions.Edit;
import Actions.Show;
import ForChart.ForPopularityChart;
import ForChart.ForQuantityChart;
import Entrance.Authorization;
import Entrance.Registration;
import WorkWithOrders.CreateOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.sql.*;

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
                JSONParser parser = new JSONParser();
                JSONObject request = (JSONObject)parser.parse(entry);
                String[] answer = entry.split(",");
                switch ((String)request.get("action"))
                {
                    case "registration":
                        Registration reg = new Registration(request, statement, out);
                        reg.registration();
                        break;
                    case "authorization":
                        Authorization auth = new Authorization(request, statement, out);
                        auth.authorization();
                        break;
                    case "show":
                        Show show = new Show(request, statement, out);
                        show.show();
                        break;
                    case "edit":
                        Edit edit = new Edit(request, statement, out);
                        edit.edit();
                        break;
                    case "show quantity chart":
                        ForQuantityChart fqc = new ForQuantityChart(request, statement, out);
                        fqc.send();
                        break;
                    case "show popularity chart":
                        ForPopularityChart fpc = new ForPopularityChart(request, statement, out);
                        fpc.send();
                        break;
                    case "delete":
                        Delete delete = new Delete(request, statement, out);
                        delete.delete();
                        break;
                    case "add":
                        Add add = new Add(request, statement, out);
                        add.add();
                        break;
                    case "create order":
                        CreateOrder createOrder = new CreateOrder(request, statement, out);
                        createOrder.createOrder();
                        break;
                    default:
                        System.out.println("Error!");
                        break;
                }
            }
            in.close();
            out.close();
            clientDialog.close();
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
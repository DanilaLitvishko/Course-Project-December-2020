import MonoThread.MonoThreadClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mercenery
 *
 */
public class Server {

    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(2525);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Сервер запущен на порте 2525");
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client));
            }
            executeIt.shutdown();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
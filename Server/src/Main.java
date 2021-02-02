import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3006);
            ArrayList<Server> servers = new ArrayList<>();

            while (true) {
                try {
                    Socket socket = server.accept();
                    Server client = new Server(socket, servers);
                    servers.add(client);
                    Thread clientThread = new Thread(client);
                    clientThread.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

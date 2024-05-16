import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static ServerSocket _server;

    private static final LinkedList<ServerWork> _serverList = new LinkedList<ServerWork>();
    public static final int PORT = 6666;
    public static void main(String[] args) throws IOException {
        _server = new ServerSocket(PORT);
        System.out.println("Server started");
        try {
            while (true) {
                Socket socket = _server.accept();
                try {
                    _serverList.add(new ServerWork(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                    socket.close();
                }
            }
        } finally {
            _server.close();
        }
    }

    public static ServerWork getSocket(int id) {
        return _serverList.get(id);
    }
}

package Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 6666; // const port
    private static LinkedList<ServerNet> _serverList = new LinkedList<ServerNet>(); //list of all nets
    private static int _IDs = 0;

    private static final BufferedReader _consoleIn = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started");
        new ConsoleReader().start();
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    Socket dataSocket = server.accept();
                    _serverList.add(new ServerNet(socket, dataSocket));
                    _IDs++;
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static void removeSocket(ServerNet socket) {
        for (ServerNet vr : _serverList) {
            if (vr.equals(socket)) {
                vr.interrupt();
                _serverList.remove(vr);
            }
        }
    }

    public static LinkedList<ServerNet> getServerList() { return _serverList; }
    public static int getSocketID(ServerNet socket) {
        return _serverList.indexOf(socket);
    }
    public static int getNewID() { return _IDs; }
    public static ServerNet getSocketByID(int id) { return _serverList.get(id); }

    private static class ConsoleReader extends Thread {
        @Override
        public void run() {
            try {
                String message;
                while (true) {
                    message = _consoleIn.readLine();
                    serverCommandHandler(message);
                }
            } catch (IOException ignore) {
            }
        }

        private static void serverCommandHandler(String message) {
            String[] splited = message.split(" ");
            switch (splited[0]) {
                case "isworking" -> {
                    System.out.println("Yes");
                }
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}
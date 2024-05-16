package Net;

import Generation.House;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.TreeMap;

public class ServerNet extends Thread {
    private Socket _socket; //user's socket
    private Socket _dataSocket;
    private int _socketID;
    private BufferedReader _in;
    private BufferedWriter _out;
    private BufferedReader _dataIn;
    private BufferedWriter _dataOut;
    private ObjectOutputStream _objectOut;
    private ObjectInputStream _objectIn;

    public ServerNet(Socket socket, Socket dataSocket) throws IOException {
        _socket = socket;
        _socketID = Server.getNewID();
        _dataSocket = dataSocket;
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
        _dataIn = new BufferedReader(new InputStreamReader(_dataSocket.getInputStream()));
        _dataOut = new BufferedWriter(new OutputStreamWriter(_dataSocket.getOutputStream()));
//        _objectOut = new ObjectOutputStream(_socket.getOutputStream());
//        _objectIn = new ObjectInputStream(_socket.getInputStream());
        start();
    }

    @Override
    public void run() {
        String message;
        try {
            while (true) {
                message = _in.readLine();
                if (message.equals("stop")) {
                    downService();
                    break;
                }
                System.out.println("Echo: " + message);
                commandHandler(message);
            }
        } catch (IOException ignore) {}
    }

    private void send(String message) {
        try {
            _out.write(message + '\n');
            _out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downService() {
        try {
            if (!_socket.isClosed()) {
                _socket.close();
                _in.close();
                _out.close();
                Server.removeSocket(this);
            }
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void commandHandler(String command) {
        String[] splited = command.split(" ");
        switch (splited[0]) {
            case "show_connections" -> {
                LinkedList<ServerNet> list = Server.getServerList();
                String msg = "";
                for (ServerNet vr : list) {
                    msg += vr._socket.getLocalSocketAddress().toString() + "//" + vr._socket.getRemoteSocketAddress() + "/ID: " + vr._socketID + " ";
                }
                send(msg);
            }
            case "copy_objects_from" -> {
                try {
                    //send("copying");
                    int from = Integer.parseInt(splited[1]);
                    ServerNet fromSocket = Server.getSocketByID(from);
                    fromSocket.send("copying_from");
                    String houses = fromSocket._dataIn.readLine();
                    send("copying_to");
                    _dataOut.write(houses);
                    _dataOut.flush();
                } catch (NumberFormatException | IOException e) {
                    send("Something went wrong");
                    e.printStackTrace();
                }
            }
            default -> {}
        }
    }

    private void sendObject(Object object) {
        try {
            _objectOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Object getObject() {
        try {
            Object object = _objectIn.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

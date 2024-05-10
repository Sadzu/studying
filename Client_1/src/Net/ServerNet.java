package Net;

import Generation.House;
import frames.ConnectionsList;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.TreeMap;

public class ServerNet extends Thread {
    private Socket _socket; //user's socket
    private int _socketID;
    private BufferedReader _in;
    private BufferedWriter _out;
    private ObjectOutputStream _objectOut;
    private ObjectInputStream _objectIn;
    private static int _copyFromID;
    private static int _copyToID;

    public ServerNet(Socket socket) throws IOException {
        _socket = socket;
        _socketID = Server.getNewID();
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
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
                if (message == null) {
                    Server.removeSocket(_socket);
                    break;
                }
                if (message.equals("stop")) {
                    downService();
                    break;
                }
                commandHandler(message);
                System.out.println("Echo: " + _socketID + ": " + message);
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
                for (ServerNet vr : Server._serverList) {
                    if (vr.equals(this)) {
                        vr.interrupt();
                        Server._serverList.remove(vr);
                    }
                }
            }
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void commandHandler(String command) {
        String[] splited = command.split(" ");
        switch (splited[0]) {
            case "get_cons_for_conslist" -> {
                sendToConsList();
            }
            case "show_connections" -> {
                LinkedList<ServerNet> list = Server.getServerList();
                String msg = "";
                for (ServerNet vr : list) {
                    msg += vr._socket.getLocalSocketAddress().toString() + "//" + vr._socket.getRemoteSocketAddress() + "/ID:" + vr._socketID + " ";
                }
                send(msg);
            }
            case "copy_objects" -> {
                try {
                    //send("copying");
                    _copyFromID = Integer.parseInt(splited[1]);
                    _copyToID = Integer.parseInt(splited[2]);
                    ServerNet fromSocket = Server.getSocketByID(_copyFromID);
                    ServerNet toSocket = Server.getSocketByID(_copyToID);
                    fromSocket.send("copying_from");
                    System.out.println("from socket id: " + _copyFromID);
                    System.out.println("to socket id: " + _copyToID);
                    System.out.println("Reading srz");
                    String hss = fromSocket._in.readLine();
                    System.out.println("Srz code");
                    System.out.println(hss);
                    toSocket.send("copying_to");
                    toSocket.send(hss);
                } catch (NumberFormatException | IOException e) {
                    send("Something went wrong");
                    e.printStackTrace();
                }
            }
            case "srz" -> {
                System.out.println("sendMotherFucker to " + _copyToID);
                Server.getSocketByID(_copyToID).send(command);
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

    public Socket getSocket() { return _socket; }
    public int getSocketID() { return _socketID; }

    @Override
    public String toString() {
        return "" + _socket.getRemoteSocketAddress() + "ID:" + _socketID;
    }

    public static String getStringConnectionsList() {
        String res = "";
        for (ServerNet vr : Server.getServerList()) {
            res += vr.toString() + " ";
        }
        return res;
    }

    private void sendToConsList() {
        String list = "info " + getStringConnectionsList();
        send(list);
    }
}

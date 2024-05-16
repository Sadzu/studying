package Net;

import Generation.Habitat;
import Generation.House;
import com.sun.source.tree.Tree;

import java.io.*;
import java.net.Socket;
import java.util.TreeMap;

public class ClientNet {
    private Socket _socket;
    private Socket _dataSocket;
    private BufferedReader _dataIn;
    private BufferedWriter _dataOut;
    private BufferedReader _in;
    private BufferedWriter _out;
    private BufferedReader _consoleIn;
    private String _ipAddress;
    private int _port;
    private int _clientID;
    private ObjectInputStream _objectIn;
    private ObjectOutputStream _objectOut;
    private Habitat _habitat;

    public ClientNet(String ipAddress, int port) {
        _ipAddress = ipAddress;
        _port = port;
        try {
            _socket = new Socket(_ipAddress, _port);
            _dataSocket = new Socket(_ipAddress, _port);
        } catch (IOException e) {
            System.out.println("Socket fail");
            e.printStackTrace();
        }
        try {
            _consoleIn = new BufferedReader(new InputStreamReader(System.in));
            _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
//            _objectIn = new ObjectInputStream(_socket.getInputStream());
//            _objectOut = new ObjectOutputStream(_socket.getOutputStream());
            _dataIn = new BufferedReader(new InputStreamReader(_dataSocket.getInputStream()));
            _dataOut = new BufferedWriter(new OutputStreamWriter(_dataSocket.getOutputStream()));
            hello();
            new ReadMessage().start();
            new WriteMessage().start();
            _clientID = Server.getNewID();
            _habitat = Habitat.getInstance(0, 0, 0, 0, 0, 0);
        } catch (IOException e) {
            ClientNet.this.downService();
        }
    }

    private void hello() {
        try {
            _out.write("Hello new socket" + '\n');
            _out.flush();
        } catch (IOException ignore) {}
    }

    private void downService() {
        try {
            if (!_socket.isClosed()) {
                _socket.close();
                _in.close();
                _out.close();
                _consoleIn.close();
            }
        } catch (IOException ignore) {}
    }

    private class ReadMessage extends Thread {
        @Override
        public void run() {
            String message;
            try {
                while (true) {
                    message = _in.readLine();
                    if (message.equals("stop")) {
                        ClientNet.this.downService();
                        break;
                    }
                    if (message.equals("copying_to")) {
                        onCopyingTo(_dataIn.readLine());
                    }
                    if (message.equals("copying_from")) {
                        onCopyingFrom();
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                ClientNet.this.downService();
                System.out.println("Something went wrong. Connection closed");
            }
        }

        @SuppressWarnings("unchecked")
        private void onCopyingFrom() {
            String houses = Habitat.getInstance().bornTimeIDsToString();
            try {
                _dataOut.write(houses + '\n');
                _dataOut.flush();
            } catch (IOException ignore) {}
        }

        private void onCopyingTo(String houses) {
            Habitat.getInstance().addFromString(houses);
            System.out.println("Copied Successfully");
            System.out.println(houses.isEmpty());
        }
    }

    public class WriteMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                String message;
                try {
                    message = _consoleIn.readLine();
                    if (message.equals("stop")) {
                        _out.write("stop" + '\n');
                        ClientNet.this.downService();
                        break;
                    } else {
                        _out.write(message + '\n');
                    }
                    _out.flush();
                } catch (IOException e) {
                    ClientNet.this.downService();
                    System.out.println("Something went wrong. Connection closed");
                }
            }
        }
    }

    public Socket getSocket() { return _socket; }
}

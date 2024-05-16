import java.io.*;
import java.net.Socket;

public class ServerWork extends Thread {
    private Socket _socket;
    private BufferedReader _in;
    private BufferedWriter _out;
    private ObjectInputStream _objectIn;
    private ObjectOutputStream _objectOut;

    public ServerWork(Socket socket) throws IOException {
        _socket = socket;
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
        _objectIn = new ObjectInputStream(_socket.getInputStream());
        _objectOut = new ObjectOutputStream(_socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        String command;
        try {
            while (true) {
                command = _in.readLine();
                _commandHandler(command);
                if (command.equals("stop")) {
                    break;
                } else {
                    _commandHandler(command);
                }
            }
        } catch (IOException e) {}
    }

    private void _commandHandler(String command) {
        String[] splited = command.split(" ");
        switch (splited[0]) {
            case "gimme_houses" -> {
                try {
                    int id = Integer.parseInt(splited[1]);
                    ServerWork secondSocket = Server.getSocket(id);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            default -> {
                _send("Invalid command");
            }
        }
    }

    private void _send(String message) {
        try {
            _out.write(message + '\n');
            _out.flush();
        } catch (IOException ignore) { ignore.printStackTrace(); }
    }
}

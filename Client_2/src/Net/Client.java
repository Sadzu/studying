package Net;

import Serializing.Serializer;
import frames.Menu;

import java.io.File;

public class Client {
    public static final String ipAddress = "localhost";
    public static final int PORT = Server.PORT;
    public static ClientNet _client;

    public static void main(String[] args) {
        _client = new ClientNet(ipAddress, PORT);
        Menu.init();
    }
}
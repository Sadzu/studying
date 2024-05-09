package Net;

import Serializing.Serializer;
import frames.Menu;

import java.io.File;

public class Client {
    public static final String ipAddress = "localhost";
    public static final int PORT = Server.PORT;

    public static void main(String[] args) {
        new ClientNet(ipAddress, PORT);
        Menu.init();
    }
}
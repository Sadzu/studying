package Net;

public class Client2 {
    public static final String ipAddress = "localhost";
    public static final int PORT = Server.PORT;

    public static void main(String[] args) {
        new ClientNet(ipAddress, PORT);
    }
}
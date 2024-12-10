package frames;

import Net.Client;
import Net.ClientNet;
import Net.Server;
import Net.ServerNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConnectionsList {
    private static JFrame _frame;
    private static TextArea _connectionsList;
    private static Timer _updater;
    private static String _connectionsMessage;

    private static void init() {
        _frame = new JFrame("Connections list");
        setConnectionsList();
        _frame.setSize(500, 500);
        Container container = _frame.getContentPane();
        container.add(_connectionsList, BorderLayout.CENTER);
        _frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        _updater = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConnectionsList();
            }
        });
    }

    private static void setConnectionsList() {
        _connectionsList = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
        getServerList();
        _connectionsList.append(_connectionsMessage);
        _connectionsList.setEditable(false);
    }

    private static void updateConnectionsList() {
        if (!_frame.isShowing()) {
            _updater.stop();
        }
        _connectionsList.replaceRange("", 0, _connectionsList.getText().length());
        _connectionsList.setEditable(true);
        getServerList();
        _connectionsList.append(_connectionsMessage);
        _connectionsList.setEditable(false);
    }

    private static void getServerList() {
//        String msg = ServerNet.getStringConnectionsList();
//        if (msg.isEmpty()) {
//            msg = "No connections\n";
//        }
//        _connectionsMessage = msg;
        Client._client.send("get_cons_for_conslist");
    }

    public static void show() {
        if (_frame == null) { init(); }
        _updater.start();
        _frame.setVisible(true);
    }

    public static void updateConnectionsMessage(String msg) {
        _connectionsMessage = msg;
        _connectionsMessage = _connectionsMessage.replaceAll(" ", "\n");
    }
}

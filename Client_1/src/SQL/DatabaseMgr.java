package SQL;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class DatabaseMgr {
    public static final String url = "jdbc:postgresql://localhost:5432/sadzudb";
    private Properties _authorization = new Properties();
    private Connection _connection;
    private int _currentID = 0;

    private static DatabaseMgr _instance;

    public static DatabaseMgr getInstance() {
        if (_instance == null) {
            _instance = new DatabaseMgr();
            _instance.launch();
        }

        return _instance;
    }

    private void launch() {
        try {
//        <----- Registration for class ----->
            Class.forName("org.postgresql.Driver");

//        <----- DB linking properties ----->
            _authorization.put("user", "sadzu");
            _authorization.put("password", "password");

//        <----- DB linking ----->
            _connection = DriverManager.getConnection(url, _authorization);
//            if (statement != null) { statement.close(); }
//            if (connection != null) { connection.close(); }
        } catch (ClassNotFoundException e) {
            System.out.println("No such class");
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
    }

    public void putItemToDatabase(String serializeString) throws SQLException {
        PreparedStatement statement = _connection.prepareStatement("INSERT INTO java.objects (serialized_str) VALUES (?)");
        statement.setString(1, serializeString);
        statement.executeUpdate();
    }

    public String getItemFromDatabase(int id) throws SQLException {
        Statement statement = _connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet table = statement.executeQuery("SELECT * FROM java.objects");
        table.beforeFirst();
        while (table.next()) {
            if (table.getString(1).equals(Integer.toString(id))) {
                return table.getString(2);
            }
        }
        table.close();
        statement.close();

        return "";
    }

    public void showTable() throws SQLException {
        Statement statement = _connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet table = statement.executeQuery("SELECT * FROM java.objects");
        table.beforeFirst();
        while (table.next()) {
            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                System.out.print(table.getString(i) + " | ");
            }
            System.out.println();
        }
    }

    public String tableToString() throws SQLException {
        Statement statement = _connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet table = statement.executeQuery("SELECT * FROM java.objects");
        table.beforeFirst();
        String res = "";
        while (table.next()) {
            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                res += table.getString(i) + " | ";
            }
            res += '\n';
        }

        statement.close();
        table.close();

        return res;
    }

    public String[] getIDs() throws SQLException {
        Statement statement = _connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet table = statement.executeQuery("SELECT * FROM java.objects");
        table.beforeFirst();
        String res = "";
        while (table.next()) {
            res += table.getString(1) + ' ';
        }

        statement.close();
        table.close();

        return res.split(" ");
    }

    public void clearTable() throws SQLException {
        Statement statement = _connection.createStatement();
        statement.executeUpdate("DELETE FROM java.objects");
    }

    public static void main(String[] args) {
        DatabaseMgr mgr = DatabaseMgr.getInstance();
        try {
            mgr.showTable();
        } catch (SQLException ignore) {}
    }
}

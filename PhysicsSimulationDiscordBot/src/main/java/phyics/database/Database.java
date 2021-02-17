package phyics.database;

import java.sql.*;

import static java.lang.System.out;

public class Database {

    private final String url;
    private final String username;
    private final String password;

    public static Connection connection;

    public Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection connect() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            out.println("Connected!");
        } catch (SQLException e) {
            out.println("Failed to connect!");
            e.printStackTrace();
        }
        return this.connection = connection;
    }
}

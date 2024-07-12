package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {
    private Connection connection;

    public MySQLConnection() {
    }

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                try {
                    String user = "root";
                    String pass = "root";
                    String database = "dorm";
                    String url = "jdbc:mysql://localhost:3306/" + database;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.connection;
    }
}

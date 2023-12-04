package dictionary.dictionary_ver2.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /**
     * Get database connection
     *
     * @return a Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

            // assign db parameters
            String url = "jdbc:mysql://localhost:3307/tu_dien";
            String password = "";
            String user = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}
package dictionary.dictionary_ver2.Database;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            String url = "jdbc:mysql://localhost:3306/tu_dien";
        String password = "Phamquan2004@";
                String user = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}
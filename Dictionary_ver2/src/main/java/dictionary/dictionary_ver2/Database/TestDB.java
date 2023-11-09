package dictionary.dictionary_ver2.Database;

import java.sql.*;

public class TestDB {
    private Connection conn = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement = conn.prepareStatement("Select * from game");
    ResultSet resultSet = preparedStatement.executeQuery();

    public TestDB() throws SQLException {
    }

    public void Test() throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id")+" "+resultSet.getString("question"));
        }
    }

    public static void main(String[] args) throws SQLException {
        TestDB test = new TestDB();
        test.Test();
    }
}

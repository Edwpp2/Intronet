package DBconfig;

import java.sql.*;

public class DbConnect {
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5433/Intranet";
    private static final String username = "postgres";
    private static final String password = "B1ume3#i";

    public static boolean isTableExists(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)"
        )) {
            preparedStatement.setString(1, tableName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        }
        return false;
    }
    public static void writeToDb(String tableName,String data){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String insertQuery = "INSERT INTO " + tableName + " (userId) VALUES (?)";

            if(!isTableExists(connection,tableName)) {
                createTable(connection,tableName);
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTable(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE " + tableName + " (" +
                    "id SERIAL PRIMARY KEY," +
                    "userId VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createTableSQL);
        }
    }

}
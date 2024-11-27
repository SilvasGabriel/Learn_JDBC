package Learn_JDBC.com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {

       DatabaseConnector connector = new DatabaseConnector();

        try {
            // Get the database connection
            Connection connection = connector.connectToDatabase();

            if (connection != null) {
                System.out.println("Successfully connected to the database!");
            }

            // Don't forget to close the connection when done
            assert connection != null;

            // Create a statement to execute SQL
            Statement statement = connection.createStatement();

            // Execute a simple query to verify
            String sql = "SELECT version();";  // PostgresSQL query to get the version

            ResultSet result = statement.executeQuery(sql);

            // Print the result
            while (result.next()) {
                System.out.println("PostgresSQL version: " + result.getString(1));
            }

            // Close the statement and result set
            result.close();
            statement.close();

            // Close the connection when done
            connection.close();

        } catch (SQLException e) {

            System.out.println("Database connection failed.");
            System.out.println(e.getErrorCode());

        }
    }

}

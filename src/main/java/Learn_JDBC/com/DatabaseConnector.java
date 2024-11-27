package Learn_JDBC.com;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {

    //Creating one connection
    public Connection connectToDatabase() throws SQLException{

        //Creating properties class here to have access to the values
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")){

            //Check if the properties are getting used and have a value
            if(input == null){
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }

            //Load the properties here
            properties.load(input);

            //Create here some variable to have access to the \resources values
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password= properties.getProperty("db.password");

            if(url == null || username == null || password == null){

                throw new SQLException("Database connection properties missing.");

            }

            return  DriverManager.getConnection(url, username, password);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }


    }

}

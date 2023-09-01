package by.mikhalenya.connectors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorPostgreSQL implements ConnectionToDB{

    @Override
    public Connection openDBConnection() {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("database.properties");
            Class.forName("org.postgresql.Driver");
            System.out.println("**** Loaded the JDBC driver");
            Connection connection = DriverManager.getConnection( configuration.getString("url"),
                    configuration.getString("username"),
                    configuration.getString("password"));
            System.out.println("**** Created a JDBC connection to the data source");
            return connection;
        } catch (ClassNotFoundException | SQLException | ConfigurationException e) {
            System.out.println("**** Failed to create a database connection");
        }
        return null;
    }

    @Override
    public void closeDBConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("**** Failed to close database connection");
        }
    }

}

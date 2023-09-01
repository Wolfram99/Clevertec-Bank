package by.mikhalenya.connectors;

import java.sql.Connection;

public interface ConnectionToDB {
    Connection openDBConnection();
    void closeDBConnection(Connection connection);
}

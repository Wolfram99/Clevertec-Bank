package by.mikhalenya.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Dao {
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;

    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }
}

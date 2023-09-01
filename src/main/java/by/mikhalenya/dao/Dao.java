package by.mikhalenya.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Data
public abstract class Dao {
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;

    protected Connection connection;

}

package by.mikhalenya.dao.daoTools;

import by.mikhalenya.entities.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InitUsers implements InitObject{
    @Override
    public Users initObject(ResultSet resultSet) {
        Users users = new Users();
        try {
            users.setIdUser(resultSet.getInt("id_user"));
        users.setName(resultSet.getString("name"));
        users.setPatronymic(resultSet.getString("patronymic"));
        users.setLastname(resultSet.getString("lastname"));
        users.setAddress(resultSet.getString("address"));
        users.setPhone(resultSet.getString("phone"));
        users.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            System.out.println("**** Failed to create an object 'Users'");
            e.printStackTrace();
        }
        return users;
    }
}

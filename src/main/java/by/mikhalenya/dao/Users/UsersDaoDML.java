package by.mikhalenya.dao.Users;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDML;
import by.mikhalenya.entities.Users;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDaoDML extends Dao implements DaoDML<Users> {

    public UsersDaoDML(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Users users) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("INSERT INTO users (id_user,name,patronymic,lastname,address,phone,email) " +
                            "VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, users.getIdUser());
            preparedStatement.setString(2,users.getName());
            preparedStatement.setString(3, users.getPatronymic());
            preparedStatement.setString(4, users.getLastname());
            preparedStatement.setString(5, users.getAddress());
            preparedStatement.setString(6, users.getPhone());
            preparedStatement.setString(7, users.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to insert the object 'Users'");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users users, int id) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("UPDATE users SET name=?,patronymic=?,lastname=?,address=?,phone=?,email=? WHERE id_user=?");
            preparedStatement.setInt(7, id);
            preparedStatement.setString(1,users.getName());
            preparedStatement.setString(2, users.getPatronymic());
            preparedStatement.setString(3, users.getLastname());
            preparedStatement.setString(4, users.getAddress());
            preparedStatement.setString(5, users.getPhone());
            preparedStatement.setString(6, users.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to update the object 'Users'");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM users WHERE id_user=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to delete the object 'Users'");
            e.printStackTrace();
        }
    }
}

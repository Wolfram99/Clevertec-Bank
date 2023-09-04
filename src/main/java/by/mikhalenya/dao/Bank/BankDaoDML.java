package by.mikhalenya.dao.Bank;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDML;
import by.mikhalenya.entities.Banks;

import java.sql.Connection;
import java.sql.SQLException;

public class BankDaoDML extends Dao implements DaoDML<Banks> {

    public BankDaoDML(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Banks banks) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("INSERT INTO banks (id_bank,name,number_of_employees) " +
                            "VALUES (?,?,?)");
            preparedStatement.setInt(1, banks.getIdBank());
            preparedStatement.setString(2, banks.getName());
            preparedStatement.setInt(3,banks.getNumberOfEmployees());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to insert the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void update(Banks banks, int id) {
        try {
            preparedStatement = getConnection().prepareStatement("UPDATE banks SET name=?,number_of_employees=? WHERE id_bank=?");
            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, banks.getName());
            preparedStatement.setInt(2,banks.getNumberOfEmployees());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to update the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM banks WHERE id_bank=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to delete the object 'Users'");
            e.printStackTrace();
        }

    }
}

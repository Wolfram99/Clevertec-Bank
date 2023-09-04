package by.mikhalenya.dao.daoTools;

import by.mikhalenya.entities.Banks;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InitBank implements InitObject{
    @Override
    public Banks initObject(ResultSet resultSet) {
        Banks banks = new Banks();
        try {
            banks.setIdBank(resultSet.getInt("id_bank"));
            banks.setName(resultSet.getString("name"));
            banks.setNumberOfEmployees(resultSet.getInt("number_of_employees"));
        } catch (SQLException e) {
            System.out.println("**** Failed to create an object 'Banks'");
            e.printStackTrace();
        }
        return banks;
    }
}

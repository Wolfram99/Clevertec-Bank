package by.mikhalenya.dao.CheckingAccountDao;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDQL;
import by.mikhalenya.dao.tollsDao.InitObject;
import by.mikhalenya.dao.tollsDao.IntiCheckingAccount;
import by.mikhalenya.entities.CheckingAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CheckingAccountDaoDQL extends Dao implements DaoDQL {
    InitObject initObject = new IntiCheckingAccount();
    public CheckingAccountDaoDQL(Connection connection) {
        this.connection = connection;

    }

    @Override
    public List<CheckingAccount> showAll() {
        List<CheckingAccount> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM checking_account");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                list.add((CheckingAccount) initObject.initObject(resultSet));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Operation failed");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CheckingAccount showById(int id) {
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM checking_account WHERE number_checking_account=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return (CheckingAccount) initObject.initObject(resultSet);
        } catch (SQLException e) {
            System.out.println("Operation failed");
            e.printStackTrace();
        }

        return (CheckingAccount) initObject.initObject(resultSet);
    }



}

package by.mikhalenya.dao.CheckingAccountDao;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDML;
import by.mikhalenya.entities.CheckingAccount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class CheckingAccountDaoDML extends Dao implements DaoDML<CheckingAccount> {
    public CheckingAccountDaoDML(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CheckingAccount checkingAccount) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("INSERT INTO checking_account (number_checking_account,account_type,status_account,opening_data,balance,account_currency,id_user,id_bank) " +
                            "VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,checkingAccount.getNumberAccount());
            preparedStatement.setString(2,checkingAccount.getAccountType());
            preparedStatement.setString(3, checkingAccount.getStatusAccount());
            preparedStatement.setDate(4,  new Date(checkingAccount.getOpeningDate().getTime()));
            preparedStatement.setDouble(5, checkingAccount.getBalance());
            preparedStatement.setString(6, checkingAccount.getAccountCurrency());
            preparedStatement.setInt(7,checkingAccount.getIdUser());
            preparedStatement.setInt(8,checkingAccount.getIdBank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to insert the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void update(CheckingAccount checkingAccount, int id) {
        try {
            preparedStatement = getConnection().prepareStatement("UPDATE checking_account SET " +
                    "account_type=?,status_account=?,opening_data=?,balance=?,account_currency=?,id_user=?,id_bank=? " +
                    "WHERE number_checking_account=?");
            preparedStatement.setInt(8, id);
            preparedStatement.setString(1,checkingAccount.getAccountType());
            preparedStatement.setString(2, checkingAccount.getStatusAccount());
            preparedStatement.setDate(3,new Date(checkingAccount.getOpeningDate().getTime()));
            preparedStatement.setDouble(4, checkingAccount.getBalance());
            preparedStatement.setString(5, checkingAccount.getAccountCurrency());
            preparedStatement.setInt(6,checkingAccount.getIdUser());
            preparedStatement.setInt(7,checkingAccount.getIdBank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to update the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement =getConnection().prepareStatement("DELETE FROM checking_account WHERE number_checking_account=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to delete the object 'Users'");
            e.printStackTrace();
        }

    }
}

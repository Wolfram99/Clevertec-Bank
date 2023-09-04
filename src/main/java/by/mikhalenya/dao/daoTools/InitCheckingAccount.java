package by.mikhalenya.dao.daoTools;

import by.mikhalenya.entities.CheckingAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InitCheckingAccount implements InitObject{
    @Override
    public CheckingAccount initObject(ResultSet resultSet) {
        CheckingAccount checkingAccount = new CheckingAccount();
        try {
            checkingAccount.setNumberAccount(resultSet.getInt("number_checking_account"));
        checkingAccount.setAccountType(resultSet.getString("account_type"));
        checkingAccount.setStatusAccount(resultSet.getString("status_account"));
        checkingAccount.setOpeningDate(resultSet.getDate("opening_data"));
        checkingAccount.setBalance(resultSet.getDouble("balance"));
        checkingAccount.setAccountCurrency(resultSet.getString("account_currency"));
        checkingAccount.setIdUser(resultSet.getInt("id_user"));
        checkingAccount.setIdBank(resultSet.getInt("id_bank"));
        } catch (SQLException e) {
            System.out.println("**** Failed to create an object 'CheckingAccount'");
            e.printStackTrace();
        }
        return checkingAccount;
    }
}

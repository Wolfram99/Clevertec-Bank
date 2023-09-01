package by.mikhalenya.dao.tollsDao;

import by.mikhalenya.entities.CheckingAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntiCheckingAccount implements InitObject{
    @Override
    public CheckingAccount initObject(ResultSet resultSet) {

        try {
            CheckingAccount checkingAccount =
                    new CheckingAccount(resultSet.getInt("number_checking_account"),
                            resultSet.getString("account_type"),
                            resultSet.getDouble("balance"),
                            resultSet.getInt("id_bank"),
                            resultSet.getString("opening_data"),
                            resultSet.getString("status_account"),
                            resultSet.getInt("id_user"),
                            resultSet.getString("account_currency")

                    );
            checkingAccount.setNumberAccount();
//            checkingAccount.setAccountType(resultSet.getString("account_type"));
//            checkingAccount.setBalance(resultSet.getDouble("balance"));
//            checkingAccount.setIdBank();
//            checkingAccount.setOpeningDate(resultSet.getString("opening_data"));
//            checkingAccount.setStatusAccount(resultSet.getString("status_account"));
//            checkingAccount.setIdUser(resultSet.getInt("id_user"));
//            checkingAccount.setAccountCurrency(resultSet.getString("account_currency"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return checkingAccount;
    }
}

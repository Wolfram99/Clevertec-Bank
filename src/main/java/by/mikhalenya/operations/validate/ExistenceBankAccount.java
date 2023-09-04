package by.mikhalenya.operations.validate;

import by.mikhalenya.entities.CheckingAccount;

public class ExistenceBankAccount {

    public static boolean existenceBankAccount(CheckingAccount checkingAccount){
        if(checkingAccount.getNumberAccount() !=0
                && checkingAccount.getAccountType() != null
                && checkingAccount.getStatusAccount() !=null
                && checkingAccount.getOpeningDate() !=null
                && checkingAccount.getAccountCurrency() !=null
                && checkingAccount.getIdUser() !=0
                && checkingAccount.getIdUser() !=0
        ){
            return true;
        }

        return false;
    }

}

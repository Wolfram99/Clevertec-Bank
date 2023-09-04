package by.mikhalenya.operations.validate;

import by.mikhalenya.entities.CheckingAccount;

public class AccountBalance {

    public static boolean accountBalance(CheckingAccount checkingAccount, double transferAmount){
        if( checkingAccount.getBalance() >= transferAmount){return true;}


        return false;
    }
}

package by.mikhalenya.operations.validate;

import by.mikhalenya.entities.CheckingAccount;

public class RelevanceBankAccount {
      public static boolean relevanceBankAccount(CheckingAccount checkingAccount){
        if(String.valueOf(checkingAccount.getStatusAccount()).equalsIgnoreCase("Открыт")){
            return true;
        }
        return false;
        }
    }


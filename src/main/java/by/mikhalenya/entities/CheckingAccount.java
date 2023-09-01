package by.mikhalenya.entities;

import lombok.Data;

@Data
public class CheckingAccount extends BankAccounts{
    private int idUser;
    private String accountCurrency;
}

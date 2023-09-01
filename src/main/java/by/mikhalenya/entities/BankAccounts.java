package by.mikhalenya.entities;

import lombok.Data;

@Data
public abstract class BankAccounts {
    private int numberAccount;
    private String accountType;
    private double balance;
    private int idBank;
    private String openingDate;
    private String statusAccount;
}

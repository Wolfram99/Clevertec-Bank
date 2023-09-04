package by.mikhalenya.entities;

import lombok.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CheckingAccount extends BankAccounts {
    private int idUser;
    private String accountCurrency;


    public CheckingAccount(int numberAccount, String accountType, double balance, int idBank, Date openingDate, String statusAccount, int idUser, String accountCurrency) {
        super(numberAccount, accountType, balance, idBank, openingDate, statusAccount);
        this.idUser = idUser;
        this.accountCurrency = accountCurrency;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "idUser=" + idUser +
                ", accountCurrency='" + accountCurrency + '\''
                + super.toString();
    }


}

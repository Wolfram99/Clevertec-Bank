package by.mikhalenya.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BankAccounts {
    private int numberAccount;
    private String accountType;
    private double balance; //тянуть как String и преобразовывать в double
    private int idBank;
    private Date openingDate;
    private String statusAccount;

    @Override
    public String toString() {
        return  ", numberAccount=" + numberAccount +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", idBank=" + idBank +
                ", openingDate='" + openingDate + '\'' +
                ", statusAccount='" + statusAccount + '\'' +
                '}';
    }


}

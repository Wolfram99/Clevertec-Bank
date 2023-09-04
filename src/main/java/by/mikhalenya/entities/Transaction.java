package by.mikhalenya.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
private int idTransaction;
private String typeTransaction;
private int senderBankAccount;
private int recipientBankAccount;
private double transferAmount; //тянуть как String и преобразовывать в double
private String transferStatus;
private Date dateTransfer;
}

package by.mikhalenya.operations;

import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDML;
import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDQL;
import by.mikhalenya.dao.Transaction.TransactionDaoDML;
import by.mikhalenya.dao.Transaction.TransactionDaoDQL;
import by.mikhalenya.documentGenerator.WriterTXT;
import by.mikhalenya.entities.CheckingAccount;
import by.mikhalenya.entities.Transaction;
import by.mikhalenya.operations.validate.ExistenceBankAccount;
import by.mikhalenya.operations.validate.RelevanceBankAccount;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Date;

//Пополнение
public class Replenishment {
    WriterTXT writerTXT = new WriterTXT();
    Connection connection;

    public Replenishment(Connection connection) {
        this.connection = connection;
    }

    public boolean replenishmentBalance(CheckingAccount checkingAccount, double amount){
        Transaction transaction = new Transaction();
        CheckingAccountDaoDML checkingAccountDaoDML = new CheckingAccountDaoDML(connection);
        TransactionDaoDML transactionDaoDML = new TransactionDaoDML(connection);
        TransactionDaoDQL transactionDaoDQL = new TransactionDaoDQL(connection);
        if(ExistenceBankAccount.existenceBankAccount(checkingAccount) == true
            && RelevanceBankAccount.relevanceBankAccount(checkingAccount) == true){

            checkingAccount.setBalance(checkingAccount.getBalance() + amount);
            checkingAccountDaoDML.update(checkingAccount, checkingAccount.getNumberAccount());

            transaction.setIdTransaction(transactionDaoDQL.lastId()+1);
            transaction.setTypeTransaction("Пополнение");
            transaction.setSenderBankAccount(checkingAccount.getNumberAccount());
            transaction.setRecipientBankAccount(checkingAccount.getNumberAccount());
            transaction.setTransferAmount(amount);
            transaction.setTransferStatus("Выполнено");
            transaction.setDateTransfer(new Date());

            transactionDaoDML.create(transaction);

            writerTXT.writeToTxtFile(transactionDaoDQL.infoTransactionsTransfer(transactionDaoDQL.lastId()));
            return true;
        }
        transaction.setIdTransaction(transactionDaoDQL.lastId()+1);
        transaction.setTypeTransaction("Пополнение");
        transaction.setSenderBankAccount(checkingAccount.getNumberAccount());
        transaction.setRecipientBankAccount(checkingAccount.getNumberAccount());
        transaction.setTransferAmount(amount);
        transaction.setTransferStatus("Ошибка");
        transaction.setDateTransfer(new Date());
        transactionDaoDML.create(transaction);

        writerTXT.writeToTxtFile(transactionDaoDQL.infoTransactionsTransfer(transactionDaoDQL.lastId()));
        System.out.println("**** It was not possible to replenish the balance, because the replenishment account did not pass verification");
        return false;
    }

}

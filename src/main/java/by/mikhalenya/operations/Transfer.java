package by.mikhalenya.operations;

import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDML;
import by.mikhalenya.dao.Transaction.TransactionDaoDML;
import by.mikhalenya.dao.Transaction.TransactionDaoDQL;
import by.mikhalenya.documentGenerator.WriterTXT;
import by.mikhalenya.entities.CheckingAccount;
import by.mikhalenya.entities.Transaction;
import by.mikhalenya.operations.validate.AccountBalance;
import by.mikhalenya.operations.validate.ExistenceBankAccount;
import by.mikhalenya.operations.validate.RelevanceBankAccount;

import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class Transfer {
    WriterTXT writerTXT = new WriterTXT();
    private Connection connection;

    public Transfer(Connection connection) {
        this.connection = connection;
    }

    public synchronized boolean transferFunds(CheckingAccount sender, CheckingAccount recipient,double amount){
        Transaction transaction = new Transaction();
        CheckingAccountDaoDML checkingAccountDaoDML = new CheckingAccountDaoDML(connection);
        TransactionDaoDML transactionDaoDML = new TransactionDaoDML(connection);
        TransactionDaoDQL taTransactionDaoDQL = new TransactionDaoDQL(connection);

        Semaphore semaphore = new Semaphore(1);
        if(ExistenceBankAccount.existenceBankAccount(sender) == true
                && RelevanceBankAccount.relevanceBankAccount(sender) == true
                && AccountBalance.accountBalance(sender,amount) == true
                &&ExistenceBankAccount.existenceBankAccount(recipient) == true
                && RelevanceBankAccount.relevanceBankAccount(recipient) == true) {

            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            checkingAccountDaoDML.update(sender, sender.getNumberAccount());
            checkingAccountDaoDML.update(recipient, recipient.getNumberAccount());

            transaction.setIdTransaction(taTransactionDaoDQL.lastId()+1);
            transaction.setTypeTransaction("Первод");
            transaction.setSenderBankAccount(sender.getNumberAccount());
            transaction.setRecipientBankAccount(recipient.getNumberAccount());
            transaction.setTransferAmount(amount);
            transaction.setTransferStatus("Выполнено");
            transaction.setDateTransfer(new Date());
            transactionDaoDML.create(transaction);

            writerTXT.writeToTxtFile(taTransactionDaoDQL.infoTransactionsTransfer(taTransactionDaoDQL.lastId()));

            return true;
        }
        transaction.setIdTransaction(taTransactionDaoDQL.lastId()+1);
        transaction.setTypeTransaction("Перевод");
        transaction.setSenderBankAccount(sender.getNumberAccount());
        transaction.setRecipientBankAccount(recipient.getNumberAccount());
        transaction.setTransferAmount(amount);
        transaction.setTransferStatus("Ошибка");
        transaction.setDateTransfer(new Date());
        transactionDaoDML.create(transaction);

        writerTXT.writeToTxtFile(taTransactionDaoDQL.infoTransactionsTransfer(taTransactionDaoDQL.lastId()));

        System.out.println("**** The transfer failed because the accounts were not verified");
        return false;
    }
}

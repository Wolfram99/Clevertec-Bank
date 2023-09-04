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

public class Withdrawal {
    WriterTXT writerTXT = new WriterTXT();
    Connection connection;

    public Withdrawal(Connection connection) {
        this.connection = connection;
    }

    public boolean withdrawalOfFunds(CheckingAccount checkingAccount, double amount){
        Transaction transaction = new Transaction();

        CheckingAccountDaoDML checkingAccountDaoDML = new CheckingAccountDaoDML(connection);
        TransactionDaoDML transactionDaoDML = new TransactionDaoDML(connection);
        TransactionDaoDQL taTransactionDaoDQL = new TransactionDaoDQL(connection);
        if(ExistenceBankAccount.existenceBankAccount(checkingAccount) == true
                && RelevanceBankAccount.relevanceBankAccount(checkingAccount) == true
                && AccountBalance.accountBalance(checkingAccount,amount) == true){

            checkingAccount.setBalance(checkingAccount.getBalance()-amount);
            checkingAccountDaoDML.update(checkingAccount,checkingAccount.getNumberAccount());

            transaction.setIdTransaction(taTransactionDaoDQL.lastId()+1);
            transaction.setTypeTransaction("Снятие");
            transaction.setSenderBankAccount(checkingAccount.getNumberAccount());
            transaction.setRecipientBankAccount(checkingAccount.getNumberAccount());
            transaction.setTransferAmount(amount);
            transaction.setTransferStatus("Выполнено");
            transaction.setDateTransfer(new Date());
            transactionDaoDML.create(transaction);

            writerTXT.writeToTxtFile(taTransactionDaoDQL.infoTransactionsTransfer(taTransactionDaoDQL.lastId()));
           return true;
        }
        transaction.setIdTransaction(taTransactionDaoDQL.lastId()+1);
        transaction.setTypeTransaction("Снятие");
        transaction.setSenderBankAccount(checkingAccount.getNumberAccount());
        transaction.setRecipientBankAccount(checkingAccount.getNumberAccount());
        transaction.setTransferAmount(amount);
        transaction.setTransferStatus("Ошибка");
        transaction.setDateTransfer(new Date());
        transactionDaoDML.create(transaction);

        writerTXT.writeToTxtFile(taTransactionDaoDQL.infoTransactionsTransfer(taTransactionDaoDQL.lastId()));
        return false;
    }

}

package by.mikhalenya.dao.daoTools;

import by.mikhalenya.entities.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InitTransaction implements InitObject{
    @Override
    public Transaction initObject(ResultSet resultSet) {
        Transaction transaction = new Transaction();
        try {
            transaction.setIdTransaction(resultSet.getInt("id_transaction"));
            transaction.setTypeTransaction(resultSet.getString("type_transaction"));
            transaction.setSenderBankAccount(resultSet.getInt("senders_bank_account"));
            transaction.setRecipientBankAccount(resultSet.getInt("recipient_bank_account"));
            transaction.setTransferAmount(resultSet.getDouble("transfer_amount"));
            transaction.setTransferStatus(resultSet.getString("transfer_status"));
            transaction.setDateTransfer(resultSet.getDate("transfer_date"));
        } catch (SQLException e) {
            System.out.println("**** Failed to create an object 'Transaction'");
            e.printStackTrace();
        }
        return transaction;
    }
}

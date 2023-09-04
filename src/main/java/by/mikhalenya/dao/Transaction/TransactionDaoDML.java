package by.mikhalenya.dao.Transaction;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDML;
import by.mikhalenya.entities.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class TransactionDaoDML extends Dao implements DaoDML<Transaction> {

    public TransactionDaoDML(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Transaction transaction) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("INSERT INTO transactions " +
                            "(id_transaction,type_transaction,senders_bank_account,recipient_bank_account,transfer_amount,transfer_status,transfer_date) " +
                            "VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, transaction.getIdTransaction());
            preparedStatement.setString(2,transaction.getTypeTransaction());
            preparedStatement.setInt(3,transaction.getSenderBankAccount());
            preparedStatement.setInt(4,transaction.getRecipientBankAccount());
            preparedStatement.setDouble(5,transaction.getTransferAmount());
            preparedStatement.setString(6,transaction.getTransferStatus());
            preparedStatement.setDate(7, new Date(transaction.getDateTransfer().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to insert the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void update(Transaction transaction, int id) {
        try {
            preparedStatement = getConnection()
                    .prepareStatement("UPDATE transactions SET type_transaction=?,senders_bank_account=?,recipient_bank_account=?,transfer_amount=?,transfer_status=?,transfer_date=? " +
                            "WHERE id_transaction=?");
            preparedStatement.setInt(7, id);
            preparedStatement.setString(1,transaction.getTypeTransaction());
            preparedStatement.setInt(2,transaction.getSenderBankAccount());
            preparedStatement.setInt(3,transaction.getRecipientBankAccount());
            preparedStatement.setDouble(4,transaction.getTransferAmount());
            preparedStatement.setString(5,transaction.getTransferStatus());
            preparedStatement.setDate(6, new Date(transaction.getDateTransfer().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to update the object 'Users'");
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM transactions WHERE id_transaction=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("**** Failed to delete the object 'Users'");
            e.printStackTrace();
        }

    }
}

package by.mikhalenya.dao.Transaction;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDQL;
import by.mikhalenya.dao.daoTools.InitObject;
import by.mikhalenya.dao.daoTools.InitTransaction;
import by.mikhalenya.dao.daoTools.InitUsers;
import by.mikhalenya.entities.Transaction;
import by.mikhalenya.entities.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoDQL extends Dao implements DaoDQL {
    InitObject initObject = new InitTransaction();
    public TransactionDaoDQL(Connection connection) {
        super(connection);
    }

//    private List<Transaction> transactionList = new ArrayList<>();
//    {
//        transactionList.add(new Transaction(1,"Перевод",1,12,15.32,"Выполнненно"));
//        transactionList.add(new Transaction(2,"Перевод",1,2,20,"Выполнненно"));
//        transactionList.add(new Transaction(3,"Перевод",2,1,211.32,"Выполнненно"));
//        transactionList.add(new Transaction(4,"Пополнненние",1,1,123,"Выполнненно"));
//        transactionList.add(new Transaction(5,"Снятие",2,2,455,"Выполнненно"));
//        transactionList.add(new Transaction(6,"Пополнненние",3,3,322,"Выполнненно"));
//        transactionList.add(new Transaction(7,"Перевод",1,12,15.32,"Ошибка"));
//    }
    @Override
    public List<Transaction> showAll(){
        List<Transaction> transactionList = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM transactions");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                transactionList.add((Transaction) initObject.initObject(resultSet));
            }
            return transactionList;
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return transactionList;
    }
    @Override
    public Transaction showById(int id){
        try {
            preparedStatement = getConnection()
                    .prepareStatement("SELECT * FROM transactions WHERE id_transaction=?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return (Transaction) initObject.initObject(resultSet);
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return (Transaction) initObject.initObject(resultSet);
    }

    public ResultSet infoTransactionsTransfer(int id){
        try {
            preparedStatement = getConnection().prepareStatement("SELECT t.id_transaction AS id_transaction, t.transfer_date AS transfer_date, t.type_transaction AS type_transaction,\n" +
                    "b1.name AS bank_name1, b2.name AS bank_name2, c1.number_checking_account AS account1, c2.number_checking_account AS account2,\n" +
                    "t.transfer_amount AS amount  FROM transactions AS t\n" +
                    "JOIN checking_account As c1 ON c1.number_checking_account = t.senders_bank_account\n" +
                    "JOIN checking_account AS c2 ON c2.number_checking_account = t.recipient_bank_account\n" +
                    "JOIN banks AS b1 ON b1.id_bank = c1.id_bank\n" +
                    "JOIN banks AS b2 ON b2.id_bank = c2.id_bank\n" +
                    "WHERE id_transaction = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }




    public int lastId(){
        int id = 0;
        try {
            preparedStatement = getConnection().prepareStatement("SELECT MAX(id_transaction) AS id_transaction  FROM transactions ");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id_transaction");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}

package by.mikhalenya.dao.Bank;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDQL;
import by.mikhalenya.dao.daoTools.InitBank;
import by.mikhalenya.dao.daoTools.InitCheckingAccount;
import by.mikhalenya.dao.daoTools.InitObject;
import by.mikhalenya.entities.Banks;
import by.mikhalenya.entities.CheckingAccount;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BankDaoDQL extends Dao implements DaoDQL {
    InitObject initObject = new InitBank();
    public BankDaoDQL(Connection connection) {
        super(connection);
    }

//    private List<Banks> banksList = new ArrayList<>();
//    {
//        banksList.add(new Banks(1,"Clever-Bank",1668));
//        banksList.add(new Banks(2,"Paritet-Bank",86));
//        banksList.add(new Banks(3,"Belarus-Bank",1200));
//        banksList.add(new Banks(4,"BPS",1130));
//        banksList.add(new Banks(5,"BSB",711));
//        banksList.add(new Banks(6,"OptiKurs",458));
//    }
@Override
public List<Banks> showAll(){
    List<Banks> banksList = new ArrayList<>();
    try {
        preparedStatement = getConnection().prepareStatement("SELECT * FROM banks");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            banksList.add((Banks) initObject.initObject(resultSet));
        }
        return banksList;
    } catch (SQLException e) {
        System.out.println("**** The 'SELECT' operation failed");
        e.printStackTrace();
    }
    return banksList;
}
    @Override
    public Banks showById(int id){
        try {
            preparedStatement = getConnection()
                    .prepareStatement("SELECT * FROM banks WHERE id_bank=?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return (Banks) initObject.initObject(resultSet);
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return (Banks) initObject.initObject(resultSet);
    }

}

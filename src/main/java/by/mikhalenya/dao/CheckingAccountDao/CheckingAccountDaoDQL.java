package by.mikhalenya.dao.CheckingAccountDao;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDQL;
import by.mikhalenya.dao.daoTools.InitCheckingAccount;
import by.mikhalenya.dao.daoTools.InitObject;
import by.mikhalenya.dao.daoTools.InitTransaction;
import by.mikhalenya.entities.CheckingAccount;
import by.mikhalenya.entities.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckingAccountDaoDQL extends Dao implements DaoDQL {
    InitObject initObject = new InitCheckingAccount();
    public CheckingAccountDaoDQL(Connection connection) {
        super(connection);
    }

//    private List<CheckingAccount> checkingAccountList = new ArrayList<>();
//    {
//        checkingAccountList.add(new CheckingAccount(1,"Текущий",186.56,1,"2233-4-15","Открыт",2,"BYN"));
//        checkingAccountList.add(new CheckingAccount(2,"Текущий",300,4,"2022-07-28","Открыт",1,"BYN" ));
//        checkingAccountList.add(new CheckingAccount(3,"Текущий",456,4,"2022-02-17","Открыт",2,"BYN"));
//        checkingAccountList.add(new CheckingAccount(4,"Текущий",1896,2,"2022-10-18","Открыт",5,"BYN"));
//        checkingAccountList.add(new CheckingAccount(5,"Текущий",567,1,"2022-02-07","Открыт",4,"BYN"));
//        checkingAccountList.add(new CheckingAccount(6,"Текущий",313,5,"2022-03-14","Открыт",3,"BYN"));
//
//        checkingAccountList.add(new CheckingAccount(7,"Текущий",909.54,6,"2233-04-25","Открыт",2,"BYN"));
//        checkingAccountList.add(new CheckingAccount(8,"Текущий",5645,1,"2022-04-23","Открыт",5,"BYN" ));
//        checkingAccountList.add(new CheckingAccount(9,"Текущий",123,3,"2022-08-19","Открыт",3,"BYN"));
//        checkingAccountList.add(new CheckingAccount(10,"Текущий",432,3,"2022-09-28","Открыт",3,"BYN"));
//        checkingAccountList.add(new CheckingAccount(11,"Текущий",343,5,"2022-12-05","Открыт",1,"BYN"));
//        checkingAccountList.add(new CheckingAccount(12,"Текущий",873,1,"2022-11-21","Открыт",1,"BYN"));
//
//    }
    @Override
    public List<CheckingAccount> showAll(){
        List<CheckingAccount> checkingAccountList = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM checking_account");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                checkingAccountList.add((CheckingAccount) initObject.initObject(resultSet));
            }
            return checkingAccountList;
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return checkingAccountList;
    }
    @Override
    public CheckingAccount showById(int id){
        try {
            preparedStatement = getConnection()
                    .prepareStatement("SELECT * FROM checking_account WHERE number_checking_account=?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return (CheckingAccount) initObject.initObject(resultSet);
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return (CheckingAccount) initObject.initObject(resultSet);
    }


    public List<CheckingAccount> searchForInterestAccrual(){
        List<CheckingAccount> list = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM checking_account WHERE CURRENT_DATE - opening_data >=?");
            preparedStatement.setInt(1,1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add((CheckingAccount) initObject.initObject(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<CheckingAccount> showByUsers(int id){
        List<CheckingAccount> checkingAccountList = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM checking_account WHERE id_user=?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                checkingAccountList.add((CheckingAccount) initObject.initObject(resultSet));
            }
            return checkingAccountList;
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return checkingAccountList;
    }
}

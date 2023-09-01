package by.mikhalenya;

import by.mikhalenya.connectors.ConnectorPostgreSQL;
import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDQL;
import by.mikhalenya.dao.Dao;
import by.mikhalenya.entities.CheckingAccount;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) {

        Connection connection = new ConnectorPostgreSQL().openDBConnection();
        CheckingAccountDaoDQL checkDao = new CheckingAccountDaoDQL(connection);

        System.out.println(checkDao.showAll().toString());
        System.out.println("---------------------------");
        System.out.println(checkDao.showById(15));

    }
}

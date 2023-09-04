package by.mikhalenya;

import by.mikhalenya.connectors.ConnectorPostgreSQL;
import by.mikhalenya.dao.Bank.BankDaoDML;
import by.mikhalenya.dao.Bank.BankDaoDQL;
import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDML;
import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDQL;
import by.mikhalenya.dao.Transaction.TransactionDaoDML;
import by.mikhalenya.dao.Transaction.TransactionDaoDQL;
import by.mikhalenya.dao.Users.UsersDaoDML;
import by.mikhalenya.dao.Users.UsersDaoDQL;

//
import by.mikhalenya.documentGenerator.WriterTXT;
import by.mikhalenya.entities.CheckingAccount;
import by.mikhalenya.entities.Users;
import by.mikhalenya.operations.InterestAccrual;
import by.mikhalenya.operations.Replenishment;
import by.mikhalenya.operations.Transfer;
import by.mikhalenya.operations.Withdrawal;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {
        //Инициализация
        Connection connection = new ConnectorPostgreSQL().openDBConnection(); //создал коннект к бд (по като нне нужен) !!! базы нет на компе поэтому это в будущем
        UsersDaoDQL usersDao = new UsersDaoDQL(connection);
        UsersDaoDML usersDaoDML = new UsersDaoDML(connection);
        TransactionDaoDQL transactionDaoDQL = new TransactionDaoDQL(connection);
        TransactionDaoDML transactionDaoDML = new TransactionDaoDML(connection);
        CheckingAccountDaoDML checkingAccountDaoDML = new CheckingAccountDaoDML(connection);
        CheckingAccountDaoDQL checkingAccountDao = new CheckingAccountDaoDQL(connection);
        BankDaoDQL bankDaoDQL = new BankDaoDQL(connection);
        BankDaoDML bankDaoDML = new BankDaoDML(connection);
        Replenishment replenishment =new Replenishment(connection);
        Withdrawal withdrawal = new Withdrawal(connection);
        Transfer transfer = new Transfer(connection);
        WriterTXT writerTXT = new WriterTXT();
        Scanner scanner = new Scanner(System.in);

        //Запуск патока для проверки начисления процентов
        InterestAccrual interestAccrual = new InterestAccrual(connection);
        interestAccrual.interestAccrual();


        //Основной цикл Программы
        while (true) {
            System.out.println("------------------------------------------------");
            for (Users u : usersDao.showAll()) {
                System.out.println(u);
            }
            System.out.println("------------------------------------------------");
            System.out.println("Enter the ID of the user from whom you want to perform the operation: ");
            int idUser = scanner.nextInt();
            System.out.println(usersDao.showById(idUser));
            for (CheckingAccount c : checkingAccountDao.showByUsers(idUser)) {
                System.out.println(c);
            }
            System.out.println("Enter the account number for which you want to perform the operation: ");
            int numberCheckingAccount = scanner.nextInt();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Operations ");
            System.out.println("1) Replenishment");
            System.out.println("2) Withdrawal");
            System.out.println("3) Transfer");
            System.out.println("------------------------------------------------");
            System.out.println("Enter the sequence number of the operation you want to perform: ");
            System.out.println("(Enter an integer!)");
            int idOperation = scanner.nextInt();
            switch (idOperation) {
                case 1 -> {
                    System.out.println("Enter the amount you want to add to your balance: ");
                    System.out.println("Enter a double, integer or real type!");
                    replenishment.replenishmentBalance(checkingAccountDao.showById(numberCheckingAccount), scanner.nextDouble());
                    System.out.println("The receipt was generated and sent to the root folder!");
                    continue;
                }
                case 2 -> {
                    System.out.println("Enter the amount you want to withdraw from your balance: ");
                    System.out.println("Enter a double, integer or real type!");
                    withdrawal.withdrawalOfFunds(checkingAccountDao.showById(numberCheckingAccount),scanner.nextDouble());
                    System.out.println("The receipt was generated and sent to the root folder!");
                    continue;
                }
                case 3 -> {
                    System.out.println("Enter the bank account to which you want to transfer funds:");
                    int recipientBanckingAccount = scanner.nextInt();
                    System.out.println("Enter the amount you want to transfer to another account: ");
                    System.out.println("Enter a double, integer or real type!");
                    double amountTransfer =scanner.nextDouble();
                    transfer.transferFunds(checkingAccountDao.showById(numberCheckingAccount),
                                            checkingAccountDao.showById(recipientBanckingAccount),
                                            amountTransfer);
                    System.out.println("The receipt was generated and sent to the root folder!");
                    continue;
                }
                default -> {
                    System.out.println("You entered the wrong value");
                    try {
                        Thread.sleep(100); //для того что бы прочитать сообщение
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
        }
    }
}

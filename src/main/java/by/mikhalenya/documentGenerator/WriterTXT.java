package by.mikhalenya.documentGenerator;



import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;


public class WriterTXT {

    public void writeToTxtFile(ResultSet resultSet) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("check//BankRecipient"+resultSet.getInt("id_transaction")+".txt"))) {
            bw.write(String.format("%30s %20s %30s  \r\n", "-", "Банковский Чек", "-"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "-", "-", "-", "-"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Номер чека", resultSet.getInt("id_transaction"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", resultSet.getDate("transfer_date"), LocalTime.now(), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Тип транзакции", resultSet.getString("type_transaction"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Банк Отправителя",resultSet.getString("bank_name1") , "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Банк получателя", resultSet.getString("bank_name2"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Счёт отправителя", resultSet.getInt("account1"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Счёт получателя", resultSet.getInt("account2"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "|", "Сумма перевода", resultSet.getDouble("amount"), "|"));
            bw.write(String.format("%20s %20s %20s %20s \r\n", "-", "-", "-", "-"));
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}



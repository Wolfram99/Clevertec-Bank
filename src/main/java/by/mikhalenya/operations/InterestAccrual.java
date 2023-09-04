package by.mikhalenya.operations;

import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDML;
import by.mikhalenya.dao.CheckingAccountDao.CheckingAccountDaoDQL;
import by.mikhalenya.entities.CheckingAccount;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.TimeUnit.SECONDS;

public class InterestAccrual {
    private boolean flag = true;
    private LocalDate dateOperation = LocalDate.now();
    static Connection connection;
    private Semaphore semaphore = new Semaphore(1);
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public InterestAccrual(Connection connection) {
        this.connection = connection;
    }

    public void interestAccrual() {
        final ScheduledFuture<?> interestAccrualC =
                scheduler.scheduleAtFixedRate(runMethods(), 0, 30, SECONDS);
    }

    public Runnable runMethods(){
        if(dateOperation.getMonthValue() < LocalDate.now().getMonthValue()){
            flag= true;
        }
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(flag == true) {
                    methodInterestAccrual();
                    semaphore.release();
                }
            }
        };
        return runnable;
    }


    public synchronized void methodInterestAccrual(){
        CheckingAccountDaoDQL checkingAccountDaoDQL = new CheckingAccountDaoDQL(connection);
        CheckingAccountDaoDML checkingAccountDaoDML = new CheckingAccountDaoDML(connection);
        PropertiesConfiguration configuration = null;
        int value = 0;
        try {
            configuration = new PropertiesConfiguration("configuration.yml");
            value = configuration.getInt("value");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
         for (CheckingAccount c: checkingAccountDaoDQL.searchForInterestAccrual()) {
            c.setBalance(c.getBalance()+c.getBalance()*value/100);
            checkingAccountDaoDML.update(c, c.getNumberAccount());
            }
        flag = false;
        dateOperation = LocalDate.now();
        //для проверки
        System.out.println(flag);
        System.out.println("Method 'methodInterestAccrual()' worked");
     }
}

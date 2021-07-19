package ru.sfedu.hiber.lab3.strategy3.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hiber.lab3.strategy1.api.Strategy1Provider;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy2.api.Strategy2Provider;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.lab3.strategy3.model.CreditAccount2;
import ru.sfedu.hiber.lab3.strategy3.model.DebitAccount2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class Strategy3ProviderTest {

    private final static Logger log = LogManager.getLogger(Strategy3ProviderTest.class);

    @Before
    public void initDb() throws IOException{
        IProvider instance = new Strategy3Provider();
        instance.deleteAll();
        CreditAccount2 creditAccount = new CreditAccount2();
        DebitAccount2 debitAccount =  new DebitAccount2();
        creditAccount.setId(1L);
        creditAccount.setOwner("Alex");
        creditAccount.setCreditLimit(new BigDecimal("10"));
        creditAccount.setBalance(new BigDecimal("105"));
        creditAccount.setInterestRate(new BigDecimal("123"));

        debitAccount.setId(2L);
        debitAccount.setBalance(new BigDecimal("1090"));
        debitAccount.setInterestRate(new BigDecimal("1245"));
        debitAccount.setOwner("Ura");
        debitAccount.setOverdraftFee(new BigDecimal("10"));

        instance.save(creditAccount, debitAccount);
    }

    @Test
    public void getByAccountsSuccess() throws IOException{
        IProvider instance =  new Strategy3Provider();
        List obj = instance.getByAccounts().get();
        log.info(obj);
        assertNotNull(obj);
    }


    @Test
    public void saveSuccess() throws IOException {
        IProvider instance = new Strategy3Provider();
        CreditAccount2 creditAccount = new CreditAccount2();
        DebitAccount2 debitAccount =  new DebitAccount2();
        creditAccount.setId(3L);
        creditAccount.setOwner("Alex");
        creditAccount.setCreditLimit(new BigDecimal("10"));
        creditAccount.setBalance(new BigDecimal("105"));
        creditAccount.setInterestRate(new BigDecimal("123"));

        debitAccount.setId(4L);
        debitAccount.setBalance(new BigDecimal("1090"));
        debitAccount.setInterestRate(new BigDecimal("1245"));
        debitAccount.setOwner("Ura");
        debitAccount.setOverdraftFee(new BigDecimal("10"));

        List<Long> ids = instance.save(creditAccount, debitAccount);
        log.info(ids);
        assertNotNull(ids);
    }

    @Test
    public void saveFail() throws IOException{
        IProvider instance =  new Strategy3Provider();
        CreditAccount2 creditAccount = new CreditAccount2();
        DebitAccount2 debitAccount =  new DebitAccount2();
        creditAccount.setId(3L);
        creditAccount.setOwner("Name10");
        creditAccount.setCreditLimit(new BigDecimal("10"));
        creditAccount.setBalance(new BigDecimal("105"));
        creditAccount.setInterestRate(new BigDecimal("123"));

        debitAccount.setId(4L);
        debitAccount.setBalance(new BigDecimal("1090"));
        debitAccount.setInterestRate(new BigDecimal("1245"));
        debitAccount.setOwner("Ura");
        debitAccount.setOverdraftFee(new BigDecimal("10"));

        List<Long> ids = instance.save(creditAccount, debitAccount);
        log.info(ids);
        assertNull(ids);
    }

    @Test
    public void getByTypeAccountSuccess() throws IOException {
        IProvider instance =  new Strategy3Provider();
        CreditAccount2 creditAccount = (CreditAccount2) instance.getByTypeAccount(1L).get();
        assertNotNull(creditAccount);
    }

    @Test
    public void getByTypeAccountFail() throws IOException{
        IProvider instance =  new Strategy3Provider();
        CreditAccount2 creditAccount = null;
        try {
            creditAccount = (CreditAccount2) instance.getByTypeAccount(12345L).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(creditAccount);
    }

    @Test
    public void updateTypeAccountSuccess() throws IOException{
        IProvider instance =  new Strategy3Provider();
        CreditAccount2 creditAccount = (CreditAccount2) instance.updateTypeAccount( 1L, "Don1").get();
        assertNotNull(creditAccount);
    }

    @Test
    public void updateTypeAccountFail() throws IOException{
        IProvider instance =  new Strategy3Provider();
        CreditAccount2 creditAccount = null;
        try{
            creditAccount = (CreditAccount2) instance.updateTypeAccount(390L, "Son").get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(creditAccount);
    }


    @Test
    public void deleteTypeAccountSuccess()throws IOException{
        IProvider instance =  new Strategy3Provider();
        boolean flag = instance.deleteTypeAccount(2L);
        assertTrue(flag);
    }

    @Test
    public void deleteTypeAccountFail()throws IOException{
        IProvider instance =  new Strategy3Provider();
        boolean flag = instance.deleteTypeAccount(555L);
        assertFalse(flag);
    }

}
package ru.sfedu.hiber.lab3.strategy2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xpath.operations.String;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hiber.lab2.api.TestEntityProviderTest;
import ru.sfedu.hiber.lab3.strategy1.api.Strategy1Provider;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Strategy2ProviderTest {

    private final static Logger log = LogManager.getLogger(Strategy2ProviderTest.class);

    @Before
    public void initDb() throws IOException{
        IProvider instance =  new Strategy2Provider();
        instance.deleteAll();
        CreditAccount1 creditAccount = new CreditAccount1();
        DebitAccount1 debitAccount =  new DebitAccount1();
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
        IProvider instance =  new Strategy2Provider();
        List obj = instance.getByAccounts().get();
        log.info(obj);
        assertNotNull(obj);
    }

    @Test
    public void saveSuccess() throws IOException {
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = new CreditAccount1();
        DebitAccount1 debitAccount =  new DebitAccount1();
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
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = new CreditAccount1();
        DebitAccount1 debitAccount =  new DebitAccount1();
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
    public void updateTypeAccountSuccess() throws IOException{
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = (CreditAccount1) instance.updateTypeAccount(CreditAccount1.class, 1L, "Don").get();
        assertNotNull(creditAccount);
    }

    @Test
    public void updateTypeAccountFail() throws IOException{
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = null;
        try{
            creditAccount = (CreditAccount1) instance.updateTypeAccount(CreditAccount1.class, 390L, "Son").get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(creditAccount);
    }

    @Test
    public void getByTypeAccountSuccess() throws IOException {
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = (CreditAccount1) instance.getByTypeAccount(CreditAccount1.class, 1L).get();
        assertNotNull(creditAccount);
    }

    @Test
    public void getByTypeAccountFail() throws IOException{
        IProvider instance =  new Strategy2Provider();
        CreditAccount1 creditAccount = null;
        try {
            creditAccount = (CreditAccount1) instance.getByTypeAccount(CreditAccount1.class, 12345L).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(creditAccount);
    }

    @Test
    public void deleteTypeAccountSuccess()throws IOException{
        IProvider instance =  new Strategy2Provider();
        boolean flag = instance.deleteTypeAccount(DebitAccount1.class, 2L);
        assertTrue(flag);
    }

    @Test
    public void deleteTypeAccountFail()throws IOException{
        IProvider instance =  new Strategy2Provider();
        boolean flag = instance.deleteTypeAccount(CreditAccount1.class, 555L);
        assertFalse(flag);
    }

}
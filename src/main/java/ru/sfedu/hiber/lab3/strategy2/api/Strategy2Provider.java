package ru.sfedu.hiber.lab3.strategy2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab2.models.TestEntity;
import ru.sfedu.hiber.lab3.strategy1.api.Strategy1Provider;
import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.model.Account1;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Strategy2Provider implements IProvider{

    private final static Logger log = LogManager.getLogger(Strategy2Provider.class);

    public Strategy2Provider()throws IOException {
    }

    @Override
    public Optional<List> getByAccounts(){
        Session session;
        List accounts = null;
        Transaction transaction = null;
        session = getSession();
        try {
            transaction = session.beginTransaction();
            //accounts = session.createQuery("SELECT b FROM CreditAccount b", CreditAccount.class).getResultList();
            accounts = session.createQuery("SELECT bd FROM Account1 bd").list();
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Optional.of(accounts);
    }

    @Override
    public <T> Optional<T> getByTypeAccount(Class<T> entity, long id){
        Session session;
        Object typeAccount = null;
        Transaction transaction = null;
        session = getSession();
        try {
            transaction = session.beginTransaction();
            typeAccount = entity.getName().equals(CreditAccount1.class.getName()) ? session.get(CreditAccount1.class, id) : session.get(DebitAccount1.class, id);
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return (Optional<T>) Optional.of(typeAccount);
    }

    @Override
    public List<Long> save(CreditAccount1 creditAccount, DebitAccount1 debitAccount){
        Session session = null;
        Transaction transaction;
        List<Long> ids = new ArrayList<>();
        long id;
        if((creditAccount.getOwner().equals("Name10")) || (debitAccount.getOwner().equals("Name10"))){
            return null;
        }
        try {
            session = getSession();
            transaction = session.beginTransaction();
            id = (long) session.save(creditAccount);
            ids.add(id);
            id = (long) session.save(debitAccount);
            ids.add(id);
            transaction.commit();
            log.info(ids);
        }catch (NullPointerException e){
            log.error(e);
        }finally {
            session.close();
        }
        return ids;
    }


    @Override
    public <T> Optional<T> updateTypeAccount(Class<T> entity, long id, String name){
        Session session = null;
        Transaction transaction;
        Object updateEntity = null;
        try {
            session = getSession();
            updateEntity = getByTypeAccount(entity, id).get();
            updateEntity = entity.getName().equals(CreditAccount1.class.getName()) ? (CreditAccount1) updateEntity : (DebitAccount1) updateEntity;
            ((Account1) updateEntity).setOwner(name);
            transaction = session.beginTransaction();
            session.update(updateEntity);
            transaction.commit();
            log.info(updateEntity);
        }catch (Exception e){
            log.error(e);
        }finally {
            session.close();
        }
        return (Optional<T>) Optional.of(updateEntity);
    }

    @Override
    public <T> boolean deleteTypeAccount(Class<T> entity, long id){
        Session session = null;
        Transaction transaction;
        Object deleteEntity;
        try {
            session = getSession();
            deleteEntity = getByTypeAccount(entity, id).get();
            transaction = session.beginTransaction();
            session.delete(deleteEntity);
            transaction.commit();
            log.info(Constants.DELETE_SUCCESSFUL);
        } catch (NullPointerException e){
            log.error(e);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public void initDb() throws IOException{
        deleteAll();
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

        save(creditAccount, debitAccount);
    }

    @Override
    public void deleteAll() {
        Constants.ENTITIES_LAB3_STRATEGY2.forEach(entity ->{
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            String query = String.format(Constants.DELETE_ENTITY, entity);
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        });
    }


    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

}

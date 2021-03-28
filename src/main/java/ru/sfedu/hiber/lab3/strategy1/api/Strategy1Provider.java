package ru.sfedu.hiber.lab3.strategy1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.model.Account1;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Strategy1Provider implements IProvider {
    private final static Logger log = LogManager.getLogger(Strategy1Provider.class);

    public Strategy1Provider()throws IOException {
    }

    @Override
    public Optional<List> getByAccounts(){
        Session session;
        List accounts = null;
        Transaction transaction = null;
        session = getSession();
        try {
            transaction = session.beginTransaction();
            accounts = session.createQuery("FROM CreditAccount").list();
            accounts.addAll(session.createQuery("FROM DebitAccount").list());
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Optional.of(accounts);
    }

    @Override
    public List<Long> save(CreditAccount creditAccount, DebitAccount debitAccount) {
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
    public <T> Optional<T> getByTypeAccount(Class<T> entity, long id){
        Session session;
        Object typeAccount = null;
        Transaction transaction = null;
        session = getSession();
        try {
            transaction = session.beginTransaction();
            typeAccount = entity.getName().equals(CreditAccount.class.getName()) ? session.get(CreditAccount.class, id) : session.get(DebitAccount.class, id);
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return (Optional<T>) Optional.of(typeAccount);
    }

    @Override
    public <T> Optional<T> updateTypeAccount(Class<T> entity, long id, String name){
        Session session = null;
        Transaction transaction;
        Object updateEntity = null;
        try {
            session = getSession();
            updateEntity = getByTypeAccount(entity, id).get();
            updateEntity = entity.getName().equals(CreditAccount.class.getName()) ? (CreditAccount) updateEntity : (DebitAccount) updateEntity;
            ((Account) updateEntity).setOwner(name);
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

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

}

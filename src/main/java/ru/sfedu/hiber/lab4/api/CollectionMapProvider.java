package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab4.models.Outfit;
import ru.sfedu.hiber.lab4.models.Outfit1;
import ru.sfedu.hiber.lab4.models.Outfit2;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionMapProvider implements IProvider{
    private final static Logger log = LogManager.getLogger(CollectionMapProvider.class);


    @Override
    public <T> List<Long> save(List<T> records) {
        Session session = null;
        Transaction transaction;
        List<Long> ids = new ArrayList<>();
        long id;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            for(int i = 0; i < records.size(); i++){
                id = (long) session.save(records.get(i));
                ids.add(id);
            }
            transaction.commit();
            log.info(ids);
        }catch (Exception e){
            log.error(e);
            return  null;
        }finally {
            session.close();
        }
        return ids;
    }

    @Override
    public Optional<Outfit2> updateOutfit2(Class entity, long id, String name) {
        Session session = null;
        Transaction transaction;
        Outfit2 updateEntity = null;
        if(name.isEmpty()){
            return null;
        }
        try {
            session = getSession();
            updateEntity = (Outfit2) getById(entity, id).get();
            updateEntity.setName(name);
            transaction = session.beginTransaction();
            session.update(updateEntity);
            transaction.commit();
        }catch (NullPointerException e){
            log.error(e);
        }finally {
            session.close();
        }
        return Optional.of(updateEntity);

    }

    @Override
    public <T> Optional<T> getById(Class className, long id) {
        Session session;
        Object record = null;
        session = getSession();
        try {
            record = session.get(className, id);
        }catch (Exception e){
            log.error(e);
        }finally {
            session.close();
        }
        return (Optional<T>) Optional.of(record);
    }

    @Override
    public <T> boolean delete(Class<T> entity, long id) {
        Session session = null;
        Transaction transaction;
        Object deleteEntity;
        try {
            session = getSession();
            deleteEntity = getById(entity, id).get();
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



    public Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }






    @Override
    public Optional<Outfit1> updateOutfit1(Class entity, long id, String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Outfit> update(Class entity, long id, String name) {
        return Optional.empty();
    }
}

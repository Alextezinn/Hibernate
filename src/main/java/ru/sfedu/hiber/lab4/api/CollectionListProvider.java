package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab4.models.Outfit3;
import ru.sfedu.hiber.lab4.models.Outfit1;
import ru.sfedu.hiber.lab4.models.Outfit2;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CollectionListProvider implements IProvider{
    private final static Logger log = LogManager.getLogger(CollectionListProvider.class);

    public CollectionListProvider(){
    }


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
            return null;
        }finally {
            session.close();
        }
        return ids;
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
    public Optional<Outfit1> updateOutfit1(Class entity, long id, String name) {
        Session session = null;
        Transaction transaction;
        Outfit1 updateEntity = null;
        if(name.isEmpty()){
            return null;
        }
        try {
            session = getSession();
            updateEntity = (Outfit1) getById(entity, id).get();
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

    @Override
    public void initDb() throws IOException {
        deleteAll();

        Outfit1 outfit = new Outfit1();
        outfit.setId(1L);
        outfit.setName("Kira");
        List<String> instruments = new ArrayList<>();
        instruments.add("instrument90");
        outfit.setInstruments(instruments);
        save(Arrays.asList(outfit));

        Outfit1 newOutfit = new Outfit1();
        newOutfit.setId(2L);
        newOutfit.setName("Alex");
        List<String> newInstruments = new ArrayList<>();
        newInstruments.add("instrument9");
        newOutfit.setInstruments(newInstruments);
        save(Arrays.asList(newOutfit));
    }

    @Override
    public void deleteAll() {
        Constants.ENTITIES_LAB4_LIST.forEach(entity ->{
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            String query = String.format(Constants.DELETE_ENTITY, entity);
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        });
    }


    public Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public Optional<Outfit3> update(Class entity, long id, String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Outfit2> updateOutfit2(Class entity, long id, String name) {
        return Optional.empty();
    }
}

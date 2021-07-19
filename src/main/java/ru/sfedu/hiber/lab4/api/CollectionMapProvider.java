package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab4.models.MeansOfMeasurement2;
import ru.sfedu.hiber.lab4.models.Outfit3;
import ru.sfedu.hiber.lab4.models.Outfit1;
import ru.sfedu.hiber.lab4.models.Outfit2;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void deleteAll() {
        Constants.ENTITIES_LAB4_MAP.forEach(entity ->{
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            String query = String.format(Constants.DELETE_ENTITY, entity);
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        });
    }

    @Override
    public void initDb() throws IOException {
        deleteAll();

        Outfit2 outfit = new Outfit2();
        outfit.setId(1L);
        outfit.setName("Jon");
        save(Arrays.asList(outfit));

        MeansOfMeasurement2 instrument1 = new MeansOfMeasurement2();
        instrument1.setId(1L);
        instrument1.setMeasurementError(0.001);
        instrument1.setNameMeansOfMeasurement("instrument3");
        instrument1.setIdOutfit(1L);
        save(Arrays.asList(instrument1));

        Outfit2 newOutfit = new Outfit2();
        newOutfit.setId(2L);
        newOutfit.setName("Bob");
        save(Arrays.asList(newOutfit));

        MeansOfMeasurement2 instrument2 = new MeansOfMeasurement2();
        instrument2.setId(2L);
        instrument2.setMeasurementError(0.001);
        instrument2.setNameMeansOfMeasurement("instrument5");
        instrument2.setIdOutfit(2L);
        save(Arrays.asList(instrument1));
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
    public Optional<Outfit3> update(Class entity, long id, String name) {
        return Optional.empty();
    }
}

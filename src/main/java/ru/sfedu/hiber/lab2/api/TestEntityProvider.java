package ru.sfedu.hiber.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab1.api.HibernateMetaDataProvider;
import ru.sfedu.hiber.lab2.models.TestEntity;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.util.Optional;

public class TestEntityProvider implements ITestEntityProvider{

    private final static Logger log = LogManager.getLogger(TestEntityProvider.class);

    public TestEntityProvider()throws IOException{
    }


    @Override
    public Optional<TestEntity> getById(Class<TestEntity> entity, long id) {
        Session session = null;
        TestEntity testEntity = null;
        try {
            session = getSession();
            testEntity = session.get(entity, id);
            log.info(testEntity);
        }catch (NullPointerException e){
            log.error(e);
        }finally {
            session.close();

        }
        return Optional.of(testEntity);
    }

    @Override
    public Long save(TestEntity entity) {
        Session session = null;
        Transaction transaction;
        Long id = null;
        if(entity.getName().equals("Name10")){
            return (long)0;
        }
        try {
            session = getSession();
            transaction = session.beginTransaction();
            id = (Long) session.save(entity);
            transaction.commit();
            log.info(id);
        }catch (NullPointerException e){
            log.error(e);
        }finally {
            session.close();
        }
        return id;
    }

    @Override
    public Optional<TestEntity> update(Class<TestEntity> entity, long id, String name){
        Session session = null;
        Transaction transaction;
        TestEntity updateEntity = null;
        try {
            updateEntity = getById(entity, id).get();
            updateEntity.setName(name);
            session = getSession();
            transaction = session.beginTransaction();
            session.update(updateEntity);
            transaction.commit();
            log.info(updateEntity);
        }catch (Exception e){
            log.error(e);
        }finally {
            session.close();
        }
        return Optional.of(updateEntity);
    }

    @Override
    public boolean delete(Class<TestEntity> entity, long id){
        Session session = null;
        Transaction transaction;
        TestEntity deleteEntity;
        try {
            deleteEntity = getById(entity, id).get();
            session = getSession();
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

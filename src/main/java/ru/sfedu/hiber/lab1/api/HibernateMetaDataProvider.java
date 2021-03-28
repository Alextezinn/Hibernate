package ru.sfedu.hiber.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.utils.HibernateUtil;

import java.io.IOException;
import java.util.List;

public class HibernateMetaDataProvider implements IMetaDataProvider{

    private final static Logger log = LogManager.getLogger(HibernateMetaDataProvider.class);

    public HibernateMetaDataProvider() throws IOException {
    }

    @Override
    public List getListSchemas(){
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(Constants.SQL_ALL_SCHEMAS);
        List resList = query.getResultList();
        session.close();
        log.info("Get schemas size {}", resList != null ? resList.size() : null);
        return resList;
    }

    @Override
    public List getListTables(){
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(Constants.SQL_ALL_TABLES);
        List resList = query.getResultList();
        session.close();
        log.info("Get tables size {}", resList != null ? resList.size() : null);
        return resList;
    }

    @Override
    public List getListTablesType(){
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(Constants.SQL_ALL_TABLES_TYPE);
        List resList = query.getResultList();
        session.close();
        log.info("Get tables type size {}", resList != null ? resList.size() : null);
        return resList;
    }

    @Override
    public List getListRoleTablesGrants(){
        Session session = getSession();
        NativeQuery query = session.createSQLQuery(Constants.SQL_ALL_ROLE_TABLES_GRANTS);
        List resList = query.getResultList();
        session.close();
        log.info("Get role tables grants size {}", resList != null ? resList.size() : null);
        return resList;
    }

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}

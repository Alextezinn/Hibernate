package ru.sfedu.hiber.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.hiber.lab2.models.TestEntity;
import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.model.Account1;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.lab3.strategy3.model.Account2;
import ru.sfedu.hiber.lab3.strategy3.model.CreditAccount2;
import ru.sfedu.hiber.lab3.strategy3.model.DebitAccount2;
import ru.sfedu.hiber.lab3.strategy4.model.Account3;
import ru.sfedu.hiber.lab3.strategy4.model.CreditAccount3;
import ru.sfedu.hiber.lab3.strategy4.model.DebitAccount3;
import ru.sfedu.hiber.lab4.models.*;
import ru.sfedu.hiber.lab5.models.*;

import java.io.File;


public class HibernateUtil {
    private final static Logger log =  LogManager.getLogger(HibernateUtil.class);
    private static final String HIBERNATE_CONFIG_PATH = System.getProperty("hiber");
    private static final String DEFAULT_HIBERNATE_CONFIG_PATH = "./src/main/resources/hibernate.cfg.xml";
    private static SessionFactory sessionFactory;
    /**
     * Создание фабрики
     *
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            File configFile = HIBERNATE_CONFIG_PATH == null ? new File(DEFAULT_HIBERNATE_CONFIG_PATH) : new File(HIBERNATE_CONFIG_PATH);
            // loads configuration and mappings
            Configuration configuration = new Configuration();
            configuration.configure(configFile);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            addEntities(metadataSources);
            metadataSources.addAnnotatedClass(CreditAccount.class); // Аннотированная сущность
            metadataSources.addAnnotatedClass(DebitAccount.class);
//            metadataSources.addAnnotatedClass(Account.class);
            metadataSources.addAnnotatedClass(Account.class);
            metadataSources.addAnnotatedClass(Account1.class);
            metadataSources.addAnnotatedClass(CreditAccount1.class);
            metadataSources.addAnnotatedClass(DebitAccount1.class);
            metadataSources.addAnnotatedClass(Account2.class);
            metadataSources.addAnnotatedClass(CreditAccount2.class);
            metadataSources.addAnnotatedClass(DebitAccount2.class);

            metadataSources.addAnnotatedClass(Account3.class);
            metadataSources.addAnnotatedClass(CreditAccount3.class);
            metadataSources.addAnnotatedClass(DebitAccount3.class);

            metadataSources.addAnnotatedClass(Outfit3.class);
            metadataSources.addAnnotatedClass(MeansOfMeasurement3.class);
            metadataSources.addAnnotatedClass(Outfit1.class);
            metadataSources.addAnnotatedClass(MeansOfMeasurement1.class);
            metadataSources.addAnnotatedClass(Outfit2.class);
            metadataSources.addAnnotatedClass(MeansOfMeasurement2.class);


            //lab5
            metadataSources.addAnnotatedClass(Outfit.class);
            metadataSources.addAnnotatedClass(PlaceOfWork.class);
            metadataSources.addAnnotatedClass(Executor.class);
            metadataSources.addAnnotatedClass(HeadOfDepartment.class);
            metadataSources.addAnnotatedClass(Customer.class);
            metadataSources.addAnnotatedClass(ElectricalMeasurement.class);
            metadataSources.addAnnotatedClass(MechanicalMeasurement.class);
            metadataSources.addAnnotatedClass(MeansMeasurement.class);
            metadataSources.addAnnotatedClass(Employee.class);
            //metadataSources.addAnnotatedClass(OutfitEmployee.class);
            //metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void addEntities(MetadataSources metadataSources){
        metadataSources.addAnnotatedClass(TestEntity.class);
    }

}



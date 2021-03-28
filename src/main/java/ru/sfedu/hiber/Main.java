package ru.sfedu.hiber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.hiber.lab1.api.HibernateMetaDataProvider;
import ru.sfedu.hiber.lab2.api.TestEntityProvider;
import ru.sfedu.hiber.lab2.models.OtherEntity;
import ru.sfedu.hiber.lab2.models.TestEntity;
import ru.sfedu.hiber.lab3.strategy1.api.Strategy1Provider;
import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.api.Strategy2Provider;
import ru.sfedu.hiber.lab3.strategy2.model.Account1;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.lab3.strategy3.api.Strategy3Provider;
import ru.sfedu.hiber.lab3.strategy3.model.CreditAccount2;
import ru.sfedu.hiber.lab3.strategy3.model.DebitAccount2;
import ru.sfedu.hiber.lab3.strategy4.api.Strategy4Provider;
import ru.sfedu.hiber.lab3.strategy4.model.CreditAccount3;
import ru.sfedu.hiber.lab3.strategy4.model.DebitAccount3;
import ru.sfedu.hiber.lab4.api.CollectionListProvider;
import ru.sfedu.hiber.lab4.api.CollectionMapProvider;
import ru.sfedu.hiber.lab4.api.CollectionSetProvider;
import ru.sfedu.hiber.lab4.models.*;
import ru.sfedu.hiber.lab5.api.DataProviderHibernate;
import ru.sfedu.hiber.lab5.models.HeadOfDepartment;
import ru.sfedu.hiber.lab5.models.MyOutfit;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        List<String> arguments = Arrays.asList(args);
        try {
            switch (arguments.get(0).toUpperCase()) {
                case Constants.LAB1:
                    HibernateMetaDataProvider provider = new HibernateMetaDataProvider();
                    chooseLab1(provider, arguments);
                    break;
                case Constants.LAB2:
                    TestEntityProvider provider1 = new TestEntityProvider();
                    chooseLab2(provider1, arguments);
                    break;
                case Constants.LAB3:
                    if (arguments.get(1).toUpperCase().equals(Constants.STRATEGY1)) {
                        Strategy1Provider provider2 = new Strategy1Provider();
                        chooseLab3Strategy1(provider2, arguments);
                    } else if (arguments.get(1).toUpperCase().equals(Constants.STRATEGY2)) {
                        Strategy2Provider provider3 = new Strategy2Provider();
                        chooseLab3Strategy2(provider3, arguments);
                    } else if (arguments.get(1).toUpperCase().equals(Constants.STRATEGY3)) {
                        Strategy3Provider provider4 = new Strategy3Provider();
                        chooseLab3Strategy3(provider4, arguments);
                    } else if (arguments.get(1).toUpperCase().equals(Constants.STRATEGY4)) {
                        Strategy4Provider provider5 = new Strategy4Provider();
                        chooseLab3Strategy4(provider5, arguments);
                    }
                    break;
                case Constants.LAB4:
                    if (arguments.get(1).toUpperCase().equals(Constants.SET)) {
                        CollectionSetProvider provider6 = new CollectionSetProvider();
                        chooseLab4Set(provider6, arguments);
                    }else if(arguments.get(1).toUpperCase().equals(Constants.LIST)){
                        CollectionListProvider provider7 = new CollectionListProvider();
                        chooseLab4List(provider7, arguments);
                    }else if(arguments.get(1).toUpperCase().equals(Constants.MAP)){
                        CollectionMapProvider provider8 = new CollectionMapProvider();
                        chooseLab4Map(provider8, arguments);
                    }
                    break;
                case Constants.LAB5:
                    DataProviderHibernate providerHibernate = new DataProviderHibernate();
                    chooseLab5(providerHibernate, arguments);
                    break;
                default:
                    log.error(Constants.ERROR_CHOOSE_LAB);
                    System.exit(1);
            }
        } catch (Exception e) {
            log.error(Constants.COMMAND_ERROR);
            log.error(e);
        }
    }

    private static void chooseLab1(HibernateMetaDataProvider provider, List<String> params) {
        try {
            switch (params.get(1).trim().toUpperCase()) {
                case Constants.GET_SCHEMAS:
                    provider.getListSchemas();
                    break;
                case Constants.GET_TABLES:
                    provider.getListTables();
                    break;
                case Constants.GET_ROLE_TABLES:
                    provider.getListRoleTablesGrants();
                    break;
                case Constants.GET_TABLES_TYPE:
                    provider.getListTablesType();
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab2(TestEntityProvider provider, List<String> params) {
        try {
            long id;
            switch (params.get(1).trim().toUpperCase()) {
                case Constants.SAVE:
                    TestEntity entity = new TestEntity();
                    OtherEntity other = new OtherEntity();
                    entity.setName(params.get(2));
                    entity.setCheck(true);
                    entity.setDateCreated(new Date());
                    entity.setDescription(params.get(3));
                    other.setCount(Double.parseDouble(params.get(4)));
                    other.setComplicationDate(new Date());
                    other.setOther(params.get(5));
                    entity.setOtherEntity(other);
                    provider.save(entity);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(2));
                    provider.getById(TestEntity.class, id);
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(2));
                    String name;
                    name = params.get(3);
                    provider.update(TestEntity.class, id, name);
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(2));
                    provider.delete(TestEntity.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab3Strategy1(Strategy1Provider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    CreditAccount creditAccount = new CreditAccount();
                    DebitAccount debitAccount = new DebitAccount();
                    creditAccount.setOwner(params.get(3));
                    creditAccount.setCreditLimit(new BigDecimal(params.get(4)));
                    creditAccount.setBalance(new BigDecimal(params.get(5)));
                    creditAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOwner(params.get(3));
                    debitAccount.setBalance(new BigDecimal(params.get(5)));
                    debitAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOverdraftFee(new BigDecimal(params.get(7)));
                    provider.save(creditAccount, debitAccount);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getByTypeAccount(CreditAccount.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name;
                    name = params.get(4);
                    provider.updateTypeAccount(CreditAccount.class, id, name);
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.deleteTypeAccount(CreditAccount.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab3Strategy2(Strategy2Provider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    CreditAccount1 creditAccount = new CreditAccount1();
                    DebitAccount1 debitAccount = new DebitAccount1();
                    creditAccount.setOwner(params.get(3));
                    creditAccount.setCreditLimit(new BigDecimal(params.get(4)));
                    creditAccount.setBalance(new BigDecimal(params.get(5)));
                    creditAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOwner(params.get(3));
                    debitAccount.setBalance(new BigDecimal(params.get(5)));
                    debitAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOverdraftFee(new BigDecimal(params.get(7)));
                    provider.save(creditAccount, debitAccount);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getByTypeAccount(CreditAccount1.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name;
                    name = params.get(4);
                    provider.updateTypeAccount(CreditAccount1.class, id, name);
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.deleteTypeAccount(CreditAccount1.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab3Strategy3(Strategy3Provider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    CreditAccount2 creditAccount = new CreditAccount2();
                    DebitAccount2 debitAccount = new DebitAccount2();
                    creditAccount.setOwner(params.get(3));
                    creditAccount.setCreditLimit(new BigDecimal(params.get(4)));
                    creditAccount.setBalance(new BigDecimal(params.get(5)));
                    creditAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOwner(params.get(3));
                    debitAccount.setBalance(new BigDecimal(params.get(5)));
                    debitAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOverdraftFee(new BigDecimal(params.get(7)));
                    provider.save(creditAccount, debitAccount);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getByTypeAccount(id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name;
                    name = params.get(4);
                    provider.updateTypeAccount(id, name);
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.deleteTypeAccount(id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab3Strategy4(Strategy4Provider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    CreditAccount3 creditAccount = new CreditAccount3();
                    DebitAccount3 debitAccount = new DebitAccount3();
                    creditAccount.setOwner(params.get(3));
                    creditAccount.setCreditLimit(new BigDecimal(params.get(4)));
                    creditAccount.setBalance(new BigDecimal(params.get(5)));
                    creditAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOwner(params.get(3));
                    debitAccount.setBalance(new BigDecimal(params.get(5)));
                    debitAccount.setInterestRate(new BigDecimal(params.get(6)));
                    debitAccount.setOverdraftFee(new BigDecimal(params.get(7)));
                    provider.save(creditAccount, debitAccount);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getByTypeAccount(CreditAccount3.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name;
                    name = params.get(4);
                    provider.updateTypeAccount(CreditAccount3.class, id, name);
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.deleteTypeAccount(CreditAccount3.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab4Set(CollectionSetProvider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    Outfit outfit = new Outfit();
                    outfit.setId(Long.parseLong(params.get(3)));
                    outfit.setName(params.get(4));
                    provider.save(Arrays.asList(outfit));

                    MeansOfMeasurement instrument1 = new MeansOfMeasurement();
                    instrument1.setId(Long.parseLong(params.get(5)));
                    instrument1.setMeasurementError(Double.parseDouble(params.get(6)));
                    instrument1.setNameMeansOfMeasurement(params.get(7));
                    instrument1.setIdOutfit(Long.parseLong(params.get(3)));
                    provider.save(Arrays.asList(instrument1));
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getById(Outfit.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name = params.get(4);
                    log.info(provider.update(Outfit.class, id, name).get());
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.delete(Outfit.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab4List(CollectionListProvider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    Outfit1 outfit = new Outfit1();
                    outfit.setId(Long.parseLong(params.get(3)));
                    outfit.setName(params.get(4));
                    List<String> instruments = new ArrayList<>();
                    instruments.add(params.get(5));
                    outfit.setInstruments(instruments);
                    provider.save(Arrays.asList(outfit));
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getById(Outfit1.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name = params.get(4);
                    log.info(provider.updateOutfit1(Outfit1.class, id, name).get());
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.delete(Outfit1.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab4Map(CollectionMapProvider provider, List<String> params) {
        try {
            long id;
            switch (params.get(2).trim().toUpperCase()) {
                case Constants.SAVE:
                    Outfit2 outfit = new Outfit2();
                    outfit.setId(Long.parseLong(params.get(3)));
                    outfit.setName(params.get(4));
                    provider.save(Arrays.asList(outfit));

                    MeansOfMeasurement2 instrument1 = new MeansOfMeasurement2();
                    instrument1.setId(Long.parseLong(params.get(5)));
                    instrument1.setMeasurementError(Double.parseDouble(params.get(6)));
                    instrument1.setNameMeansOfMeasurement(params.get(7));
                    instrument1.setIdOutfit(Long.parseLong(params.get(3)));
                    provider.save(Arrays.asList(instrument1));
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(3));
                    log.info(provider.getById(Outfit2.class, id).get());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(3));
                    String name = params.get(4);
                    log.info(provider.updateOutfit2(Outfit2.class, id, name).get());
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(3));
                    provider.delete(Outfit2.class, id);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static void chooseLab5(DataProviderHibernate provider, List<String> params) {
        try{
            List<Long> employees;
            long id;
            switch (params.get(1).trim().toUpperCase()) {
                case Constants.SAVE:
                    employees = provider.stringToList(params.get(6));
                    provider.createOutfit(new Date(), Long.parseLong(params.get(2)), Long.parseLong(params.get(3)), Long.parseLong(params.get(4)), Long.parseLong(params.get(5)), employees);
                    break;
                case Constants.GET_BY_ID:
                    id = Long.parseLong(params.get(2));
                    MyOutfit outfit = (MyOutfit) provider.getById(MyOutfit.class, id).get();
                    log.info(outfit.getExecutor());
                    break;
                case Constants.UPDATE:
                    id = Long.parseLong(params.get(2));
                    employees = provider.stringToList(params.get(3));
                    log.info(provider.editOutfit(id, employees, Long.parseLong(params.get(4))).get(1));
                    break;
                case Constants.DELETE:
                    id = Long.parseLong(params.get(2));
                    log.info(provider.deleteOutfit(id, Long.parseLong(params.get(3))));
                    break;
            }
        }catch (Exception e){
            log.error(e);
        }
    }
}
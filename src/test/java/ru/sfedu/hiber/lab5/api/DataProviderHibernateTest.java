package ru.sfedu.hiber.lab5.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

import ru.sfedu.hiber.lab5.models.*;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

public class DataProviderHibernateTest {
    private final static Logger log = LogManager.getLogger(DataProviderHibernateTest.class);

    @Before
    public void initDb(){
//        DataProviderHibernate provider = new DataProviderHibernate();
//
//        List<Customer> customers = new ArrayList<>();
//        Customer customer = new Customer();
//        customer.setName("fifa");
//        customers.add(customer);
//        Customer customer1 = new Customer();
//        customer1.setName("Jon");
//        customers.add(customer1);
//        Customer customer2 = new Customer();
//        customer2.setName("Nicola");
//        customers.add(customer2);
//        Customer customer3 = new Customer();
//        customer3.setName("Bob");
//        customers.add(customer3);
//
//        List<PlaceOfWork> placeOfWorks = new ArrayList<>();
//        PlaceOfWork placeOfWork = new PlaceOfWork();
//        placeOfWork.setName("Park");
//        placeOfWorks.add(placeOfWork);
//        PlaceOfWork placeOfWork1 = new PlaceOfWork();
//        placeOfWork1.setName("Unic");
//        placeOfWorks.add(placeOfWork1);
//        PlaceOfWork placeOfWork2 = new PlaceOfWork();
//        placeOfWork2.setName("Sea");
//        placeOfWorks.add(placeOfWork2);
//        PlaceOfWork placeOfWork3 = new PlaceOfWork();
//        placeOfWork3.setName("workplace");
//        placeOfWorks.add(placeOfWork3);
//
//        List<Employee> employees = new ArrayList<>();
//        Employee employee = new Employee();
//        employee.setName("Sasha");
//        employee.setSalary(32000);
//        employees.add(employee);
//        Employee employee1 = new Employee();
//        employee1.setName("Alex");
//        employee1.setSalary(34000);
//        employees.add(employee1);
//        Employee employee2 = new Employee();
//        employee2.setName("Kik");
//        employee2.setSalary(28000);
//        employees.add(employee2);
//        Employee employee3 = new Employee();
//        employee3.setName("Pillow");
//        employee3.setSalary(28500);
//        employees.add(employee3);
//
//        List<HeadOfDepartment> headOfDepartments = new ArrayList<>();
//        HeadOfDepartment headOfDepartment = new HeadOfDepartment();
//        headOfDepartment.setId(6);
//        headOfDepartment.setName("Lusi");
//        headOfDepartment.setSalary(22000);
//        headOfDepartments.add(headOfDepartment);
//        HeadOfDepartment headOfDepartment1 = new HeadOfDepartment();
//        headOfDepartment1.setId(7);
//        headOfDepartment1.setName("Zuzu");
//        headOfDepartment1.setSalary(23000);
//        headOfDepartments.add(headOfDepartment1);
//        HeadOfDepartment headOfDepartment2 = new HeadOfDepartment();
//        headOfDepartment2.setId(8);
//        headOfDepartment2.setName("Tic");
//        headOfDepartment2.setSalary(23500);
//        headOfDepartments.add(headOfDepartment2);
//        HeadOfDepartment headOfDepartment3 = new HeadOfDepartment();
//        headOfDepartment3.setId(9);
//        headOfDepartment3.setName("Robert");
//        headOfDepartment3.setSalary(29000);
//        headOfDepartments.add(headOfDepartment3);
//
//        provider.save(headOfDepartments);
//
//        headOfDepartment = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 6).get();
//        headOfDepartment1 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 7).get();
//        headOfDepartment2 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 8).get();
//        headOfDepartment3 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 9).get();
//
//        List<Executor> executors = new ArrayList<>();
//        Executor executor = new Executor();
//        executor.setPhoneNumber("799999999");
//        executor.setHeadOfDepartment(headOfDepartment);
//        executors.add(executor);
//        Executor executor1 = new Executor();
//        executor1.setPhoneNumber("799999998");
//        executor1.setHeadOfDepartment(headOfDepartment1);
//        executors.add(executor1);
//        Executor executor2 = new Executor();
//        executor2.setPhoneNumber("799999997");
//        executor2.setHeadOfDepartment(headOfDepartment2);
//        executors.add(executor2);
//        Executor executor3 = new Executor();
//        executor3.setPhoneNumber("799999996");
//        executor3.setHeadOfDepartment(headOfDepartment3);
//        executors.add(executor3);
//        provider.save(executors);
//
//        List<MeansMeasurement> meansMeasurements = new ArrayList<>();
//
//        MechanicalMeasurement mechanicalMeasurement = new MechanicalMeasurement();
//        mechanicalMeasurement.setModel("x220");
//        mechanicalMeasurement.setName("other");
//        mechanicalMeasurement.setMeasurementError(0.0001);
//
//        MechanicalMeasurement mechanicalMeasurement1 = new MechanicalMeasurement();
//        mechanicalMeasurement1.setModel("x221");
//        mechanicalMeasurement1.setName("other1");
//        mechanicalMeasurement1.setMeasurementError(0.0001);
//
//        ElectricalMeasurement electricalMeasurement = new ElectricalMeasurement();
//        electricalMeasurement.setPower(30);
//        electricalMeasurement.setModel("p001");
//        electricalMeasurement.setName("rison");
//        electricalMeasurement.setMeasurementError(0.001);
//
//        ElectricalMeasurement electricalMeasurement1 = new ElectricalMeasurement();
//        electricalMeasurement1.setPower(40);
//        electricalMeasurement1.setModel("p002");
//        electricalMeasurement1.setName("rison1");
//        electricalMeasurement1.setMeasurementError(0.001);
//
//        meansMeasurements.add(mechanicalMeasurement);
//        meansMeasurements.add(mechanicalMeasurement1);
//        meansMeasurements.add(electricalMeasurement);
//        meansMeasurements.add(electricalMeasurement1);
//
//        provider.save(customers);
//        provider.save(placeOfWorks);
//        provider.save(employees);
//        provider.save(meansMeasurements);
    }

    @Test
    public void createOutfitSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = provider.stringToList("258,259");
        assertTrue((boolean) provider.createOutfit(new Date(), 234, 5, 238, 246, idEmployees).get(0));
    }

    @Test
    public void createOutfitFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = new ArrayList<>();
        idEmployees.add((long)20);
        assertFalse((boolean) provider.createOutfit(new Date(), 52, 35, 30, 47, idEmployees).get(0));
    }

    @Test
    public void getByIdSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        MyOutfit outfit;
        outfit = (MyOutfit) provider.getById(MyOutfit.class, 262L).get();
        log.info(outfit.getDateStartWork());
        assertNotNull(outfit);
    }

    @Test
    public void getByIdFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        MyOutfit outfit = null;
        try {
            outfit = (MyOutfit) provider.getById(MyOutfit.class, 3500L).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void editOutfitSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = provider.stringToList("275,276");
        assertTrue((boolean) provider.editOutfit(262, idEmployees, 2).get(0));
    }

    @Test
    public void editOutfitFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = provider.stringToList("56,67");
        assertFalse((boolean) provider.editOutfit(909, idEmployees, 35).get(0));
    }

    @Test
    public void deleteOutfitSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        assertTrue(provider.deleteOutfit(284, 4));
    }

    @Test
    public void deleteOutfitFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        assertFalse(provider.deleteOutfit(1234, 110));
    }

    @Test
    public void compareTimesSql() {
        DataProviderHibernate provider = new DataProviderHibernate();
        final int times = 50;
        long time_hql = 0;
        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            final List<Customer> customersSql = provider.getAllCustomerHql();
            long end = System.currentTimeMillis();
            time_hql += end - start;
        }

        long time_sql = 0;
        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            final List<Customer> customersSql = provider.getAllCustomerSql();
            long end = System.currentTimeMillis();
            time_sql += end - start;
        }

        long time_criteria = 0;
        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            final List<Customer> customersCriteria = provider.getAllCustomerCriteria();
            long end = System.currentTimeMillis();
            time_criteria += end - start;
        }
        log.info("HQL {} SQL {} Criteria {}", time_hql, time_sql, time_criteria);
    }

    @Test
    public void queriesToDb(){
        DataProviderHibernate provider = new DataProviderHibernate();
        BigInteger count_sql = provider.getPlaceOfWorkCountNative();
        long count_hql = provider.getPlaceOfWorkCountHQL();
        long count_criteria =  provider.getPlaceOfWorkCountCriteria();
        log.info("SQL {} HQL {} Criteria {}", count_sql, count_hql, count_criteria);
    }

















































//    @Test
//    public void saveSuccess(){
//        DataProviderHibernate provider = new DataProviderHibernate();
////
//        Customer customer = new Customer();
//        customer.setName("gfjgyj");
//        provider.save(Arrays.asList(customer));
//
//        //Employee employee = (Employee) provider.getById(Employee.class, 185L).get();
////        Employee employee1 = (Employee) provider.getById(Employee.class, 172L).get();
//
//        //Customer customer = (Customer) provider.getById(Customer.class, 183L).get();
//
//        //log.info(employee.getName());
////        MyOutfit outfit = new MyOutfit();
////        MyOutfit outfit = (MyOutfit) provider.getById(MyOutfit.class, 164L).get();
////        log.info(outfit.getEmployees());
////        Set<MyOutfit> outfits = new HashSet<>();
////        outfits.add(outfit);
////        employee.setOutfits(outfits);
//
//
////        MyOutfit outfit = new MyOutfit();
////        Employee employee = (Employee) provider.getById(Employee.class, 156L).get();
////        Set<Employee> employeesOutfit = new HashSet<Employee>();
////        employeesOutfit.add(employee);
////        outfit.setEmployees(employeesOutfit);
////        provider.save(Arrays.asList(outfit));
//
//
//       // MyOutfit outfit = (MyOutfit) provider.getById(MyOutfit.class, 187L).get();
////        log.info(outfit);
////        Set<Employee> employees = new HashSet<>();
////        employees.add(employee);
////        outfit.setEmployees(employees);
////        provider.save(Arrays.asList(outfit));
//
////        Session session = provider.getSession();
////        Transaction transaction = session.beginTransaction();
////        session.update(outfit);
////        transaction.commit();
//
////        Session session = provider.getSession();
////        Transaction transaction = session.beginTransaction();
////        //employee.setName("gfhjghjfhgh9");
////        session.delete(outfit);
////        transaction.commit();
//
//       // log.info(outfit.getId());
//
////        Employee employee = new Employee();
////        employee.setName("gfg");
////        employee.setSalary(32000);
////        provider.save(Arrays.asList(employee));
////
////        HeadOfDepartment headOfDepartment = new HeadOfDepartment();
////        headOfDepartment.setId(1);
////        headOfDepartment.setName("Lusi");
////        headOfDepartment.setSalary(20000);
//
////        HeadOfDepartment headOfDepartment = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 188).get();
////        Executor executor = new Executor();
////        executor.setPhoneNumber("799999999");
////        executor.setHeadOfDepartment(headOfDepartment);
//        //provider.save(Arrays.asList(executor));
//        //provider.save(Arrays.asList(headOfDepartment));
//
////        MyOutfit outfit = new MyOutfit();
////       // Set<Customer> customerOutfit = new HashSet<>();
////        Set<Employee> employeesOutfit = new HashSet<Employee>();
////        employeesOutfit.add(employee);
////        //employeesOutfit.add(employee1);
////        //customerOutfit.add(customer);
////        outfit.setEmployees(employeesOutfit);
////        outfit.setCustomer(customer);
////        provider.save(Arrays.asList(outfit));
////
////
//////        //employee.setOutfits(outfits);
////        provider.save(Arrays.asList(outfit));
//    }



}
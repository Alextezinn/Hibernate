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
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Outfit> outfits = new ArrayList<>();
        outfits = provider.getListOutfit();
        if(!outfits.isEmpty()){
            provider.delete(outfits);
        }
        provider.deleteAll();

        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("fifa");
        customers.add(customer);
        Customer customer1 = new Customer();
        customer1.setId(2);
        customer1.setName("Jon");
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setId(3);
        customer2.setName("Nicola");
        customers.add(customer2);
        Customer customer3 = new Customer();
        customer3.setId(4);
        customer3.setName("Bob");
        customers.add(customer3);

        Customer customer4 = new Customer();
        customer4.setId(5);
        customer4.setName("fifa");
        customers.add(customer4);
        Customer customer5 = new Customer();
        customer5.setId(6);
        customer5.setName("Jon");
        customers.add(customer5);
        Customer customer6 = new Customer();
        customer6.setId(7);
        customer6.setName("Nicola");
        customers.add(customer6);
        Customer customer7 = new Customer();
        customer7.setId(8);
        customer7.setName("Bob");
        customers.add(customer7);

        List<PlaceOfWork> placeOfWorks = new ArrayList<>();
        PlaceOfWork placeOfWork = new PlaceOfWork();
        placeOfWork.setId(10);
        placeOfWork.setName("Park");
        placeOfWorks.add(placeOfWork);
        PlaceOfWork placeOfWork1 = new PlaceOfWork();
        placeOfWork1.setId(11);
        placeOfWork1.setName("Unic");
        placeOfWorks.add(placeOfWork1);
        PlaceOfWork placeOfWork2 = new PlaceOfWork();
        placeOfWork2.setId(12);
        placeOfWork2.setName("Sea");
        placeOfWorks.add(placeOfWork2);
        PlaceOfWork placeOfWork3 = new PlaceOfWork();
        placeOfWork3.setId(13);
        placeOfWork3.setName("workplace");
        placeOfWorks.add(placeOfWork3);

        PlaceOfWork placeOfWork4 = new PlaceOfWork();
        placeOfWork4.setId(14);
        placeOfWork4.setName("Park");
        placeOfWorks.add(placeOfWork4);
        PlaceOfWork placeOfWork5 = new PlaceOfWork();
        placeOfWork5.setId(15);
        placeOfWork5.setName("Unic");
        placeOfWorks.add(placeOfWork5);
        PlaceOfWork placeOfWork6 = new PlaceOfWork();
        placeOfWork6.setId(16);
        placeOfWork6.setName("Sea");
        placeOfWorks.add(placeOfWork6);
        PlaceOfWork placeOfWork7 = new PlaceOfWork();
        placeOfWork7.setId(17);
        placeOfWork7.setName("workplace");
        placeOfWorks.add(placeOfWork7);

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(20);
        employee.setName("Sasha");
        employee.setSalary(32000);
        employees.add(employee);
        Employee employee1 = new Employee();
        employee1.setId(21);
        employee1.setName("Alex");
        employee1.setSalary(34000);
        employees.add(employee1);
        Employee employee2 = new Employee();
        employee2.setId(22);
        employee2.setName("Kik");
        employee2.setSalary(28000);
        employees.add(employee2);
        Employee employee3 = new Employee();
        employee3.setId(23);
        employee3.setName("Pillow");
        employee3.setSalary(28500);
        employees.add(employee3);

        Employee employee4 = new Employee();
        employee4.setId(24);
        employee4.setName("Sasha");
        employee4.setSalary(32000);
        employees.add(employee4);
        Employee employee5 = new Employee();
        employee5.setId(25);
        employee5.setName("Alex");
        employee5.setSalary(34000);
        employees.add(employee5);
        Employee employee6 = new Employee();
        employee6.setId(26);
        employee6.setName("Kik");
        employee6.setSalary(28000);
        employees.add(employee6);
        Employee employee7 = new Employee();
        employee7.setId(27);
        employee7.setName("Pillow");
        employee7.setSalary(28500);
        employees.add(employee7);

        List<HeadOfDepartment> headOfDepartments = new ArrayList<>();
        HeadOfDepartment headOfDepartment = new HeadOfDepartment();
        headOfDepartment.setId(40);
        headOfDepartment.setName("Lusi");
        headOfDepartment.setSalary(22000);
        headOfDepartments.add(headOfDepartment);
        HeadOfDepartment headOfDepartment1 = new HeadOfDepartment();
        headOfDepartment1.setId(41);
        headOfDepartment1.setName("Zuzu");
        headOfDepartment1.setSalary(23000);
        headOfDepartments.add(headOfDepartment1);
        HeadOfDepartment headOfDepartment2 = new HeadOfDepartment();
        headOfDepartment2.setId(42);
        headOfDepartment2.setName("Tic");
        headOfDepartment2.setSalary(23500);
        headOfDepartments.add(headOfDepartment2);
        HeadOfDepartment headOfDepartment3 = new HeadOfDepartment();
        headOfDepartment3.setId(43);
        headOfDepartment3.setName("Robert");
        headOfDepartment3.setSalary(29000);
        headOfDepartments.add(headOfDepartment3);

        HeadOfDepartment headOfDepartment4 = new HeadOfDepartment();
        headOfDepartment4.setId(44);
        headOfDepartment4.setName("Lusi");
        headOfDepartment4.setSalary(22000);
        headOfDepartments.add(headOfDepartment4);
        HeadOfDepartment headOfDepartment5 = new HeadOfDepartment();
        headOfDepartment5.setId(45);
        headOfDepartment5.setName("Zuzu");
        headOfDepartment5.setSalary(23000);
        headOfDepartments.add(headOfDepartment5);
        HeadOfDepartment headOfDepartment6 = new HeadOfDepartment();
        headOfDepartment6.setId(46);
        headOfDepartment6.setName("Tic");
        headOfDepartment6.setSalary(23500);
        headOfDepartments.add(headOfDepartment6);
        HeadOfDepartment headOfDepartment7 = new HeadOfDepartment();
        headOfDepartment7.setId(47);
        headOfDepartment7.setName("Robert");
        headOfDepartment7.setSalary(29000);
        headOfDepartments.add(headOfDepartment7);

        provider.save(headOfDepartments);

        headOfDepartment = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 40).get();
        headOfDepartment1 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 41).get();
        headOfDepartment2 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 42).get();
        headOfDepartment3 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 43).get();

        headOfDepartment4 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 44).get();
        headOfDepartment5 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 45).get();
        headOfDepartment6 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 46).get();
        headOfDepartment7 = (HeadOfDepartment) provider.getById(HeadOfDepartment.class, 47).get();

        List<Executor> executors = new ArrayList<>();
        Executor executor = new Executor();
        executor.setId(50);
        executor.setPhoneNumber("799999999");
        executor.setHeadOfDepartment(headOfDepartment);
        executors.add(executor);
        Executor executor1 = new Executor();
        executor1.setId(51);
        executor1.setPhoneNumber("799999998");
        executor1.setHeadOfDepartment(headOfDepartment1);
        executors.add(executor1);
        Executor executor2 = new Executor();
        executor2.setId(52);
        executor2.setPhoneNumber("799999997");
        executor2.setHeadOfDepartment(headOfDepartment2);
        executors.add(executor2);
        Executor executor3 = new Executor();
        executor3.setId(53);
        executor3.setPhoneNumber("799999996");
        executor3.setHeadOfDepartment(headOfDepartment3);
        executors.add(executor3);

        Executor executor4 = new Executor();
        executor4.setId(54);
        executor4.setPhoneNumber("799999999");
        executor4.setHeadOfDepartment(headOfDepartment4);
        executors.add(executor4);
        Executor executor5 = new Executor();
        executor5.setId(55);
        executor5.setPhoneNumber("799999998");
        executor5.setHeadOfDepartment(headOfDepartment5);
        executors.add(executor5);
        Executor executor6 = new Executor();
        executor6.setId(56);
        executor6.setPhoneNumber("799999997");
        executor6.setHeadOfDepartment(headOfDepartment6);
        executors.add(executor6);
        Executor executor7 = new Executor();
        executor7.setId(57);
        executor7.setPhoneNumber("799999996");
        executor7.setHeadOfDepartment(headOfDepartment7);
        executors.add(executor7);
        provider.save(executors);

        List<MeansMeasurement> meansMeasurements = new ArrayList<>();

        MechanicalMeasurement mechanicalMeasurement = new MechanicalMeasurement();
        mechanicalMeasurement.setId(30);
        mechanicalMeasurement.setModel("x220");
        mechanicalMeasurement.setName("other");
        mechanicalMeasurement.setMeasurementError(0.0001);

        MechanicalMeasurement mechanicalMeasurement1 = new MechanicalMeasurement();
        mechanicalMeasurement1.setId(31);
        mechanicalMeasurement1.setModel("x221");
        mechanicalMeasurement1.setName("other1");
        mechanicalMeasurement1.setMeasurementError(0.0001);

        MechanicalMeasurement mechanicalMeasurement2 = new MechanicalMeasurement();
        mechanicalMeasurement2.setId(34);
        mechanicalMeasurement2.setModel("x220");
        mechanicalMeasurement2.setName("other");
        mechanicalMeasurement2.setMeasurementError(0.0001);

        MechanicalMeasurement mechanicalMeasurement3 = new MechanicalMeasurement();
        mechanicalMeasurement3.setId(35);
        mechanicalMeasurement3.setModel("x221");
        mechanicalMeasurement3.setName("other1");
        mechanicalMeasurement3.setMeasurementError(0.0001);

        ElectricalMeasurement electricalMeasurement = new ElectricalMeasurement();
        electricalMeasurement.setId(32);
        electricalMeasurement.setPower(30);
        electricalMeasurement.setModel("p001");
        electricalMeasurement.setName("rison");
        electricalMeasurement.setMeasurementError(0.001);

        ElectricalMeasurement electricalMeasurement1 = new ElectricalMeasurement();
        electricalMeasurement1.setId(33);
        electricalMeasurement1.setPower(40);
        electricalMeasurement1.setModel("p002");
        electricalMeasurement1.setName("rison1");
        electricalMeasurement1.setMeasurementError(0.001);

        ElectricalMeasurement electricalMeasurement2 = new ElectricalMeasurement();
        electricalMeasurement2.setId(36);
        electricalMeasurement2.setPower(30);
        electricalMeasurement2.setModel("p001");
        electricalMeasurement2.setName("rison");
        electricalMeasurement2.setMeasurementError(0.001);

        ElectricalMeasurement electricalMeasurement3 = new ElectricalMeasurement();
        electricalMeasurement3.setId(37);
        electricalMeasurement3.setPower(40);
        electricalMeasurement3.setModel("p002");
        electricalMeasurement3.setName("rison1");
        electricalMeasurement3.setMeasurementError(0.001);

        meansMeasurements.add(mechanicalMeasurement);
        meansMeasurements.add(mechanicalMeasurement1);
        meansMeasurements.add(mechanicalMeasurement2);
        meansMeasurements.add(mechanicalMeasurement3);
        meansMeasurements.add(electricalMeasurement);
        meansMeasurements.add(electricalMeasurement1);
        meansMeasurements.add(electricalMeasurement2);
        meansMeasurements.add(electricalMeasurement3);

        provider.save(customers);
        provider.save(placeOfWorks);
        provider.save(employees);
        provider.save(meansMeasurements);

        List<Long> idEmployees = provider.stringToList("20,21");
        provider.createOutfit(new Date(), 1, 40, 10, 30, idEmployees, 1).get(1);

        List<Long> idEmployee = provider.stringToList("22,23");
        provider.createOutfit(new Date(), 2, 41, 11, 31, idEmployee, 2).get(1);
    }

    @Test
    public void createOutfitSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = provider.stringToList("24,25");
        assertTrue((boolean) provider.createOutfit(new Date(), 3, 42, 12, 32, idEmployees, 3).get(0));
    }

    @Test
    public void createOutfitFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = new ArrayList<>();
        idEmployees.add((long)20);
        assertFalse((boolean) provider.createOutfit(new Date(), 52, 35, 30, 47, idEmployees, 3).get(0));
    }

    @Test
    public void getByIdSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        Outfit outfit;
        outfit = (Outfit) provider.getById(Outfit.class, 1L).get();
        log.info(outfit.getDateStartWork());
        assertNotNull(outfit);
    }

    @Test
    public void getByIdFail(){
        DataProviderHibernate provider = new DataProviderHibernate();
        Outfit outfit = null;
        try {
            outfit = (Outfit) provider.getById(Outfit.class, 3500L).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void editOutfitSuccess(){
        DataProviderHibernate provider = new DataProviderHibernate();
        List<Long> idEmployees = provider.stringToList("26,27");
        assertTrue((boolean) provider.editOutfit(1, idEmployees, 43).get(0));
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
        assertTrue(provider.deleteOutfit(2, 41));
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
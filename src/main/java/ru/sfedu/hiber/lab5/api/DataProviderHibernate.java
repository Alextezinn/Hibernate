package ru.sfedu.hiber.lab5.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.NativeQuery;
import ru.sfedu.hiber.Constants;
import ru.sfedu.hiber.lab5.models.*;
import ru.sfedu.hiber.utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProviderHibernate implements IProvider{
    private final static Logger log = LogManager.getLogger(DataProviderHibernate.class);

    public DataProviderHibernate() {
    }


    @Override
    public List<Object> createOutfit(Date dateStartWork, long idCustomer, long idHeadOfDepartment, long idPlaceWork,
                                     long idTypeMeasurementInstrument, List<Long> idEmployees, long id) throws NoSuchElementException {
        Outfit outfit = new Outfit();
        try{
            List<Employee> listEmployee = getListEmployee();
            List getHeadDepartment = findExistsHeadOfDepartmentId(idHeadOfDepartment);
            List getEmployeesOutfit = findExistsEmployeeId(idEmployees);
            List getCustomerOutfit = findExistsCustomerId(idCustomer);
            List getPlaceWorkInOutfit = findPlaceWorkId(idPlaceWork);
            List getMeasuremantInstrument = checkIdTypeMeasurementInstrument(idTypeMeasurementInstrument);
            if ((boolean) getHeadDepartment.get(0) || (boolean) getEmployeesOutfit.get(0) || (boolean) getCustomerOutfit.get(0) || (boolean) getPlaceWorkInOutfit.get(0) || checkIdEmployeesNotNull(idEmployees) || checkCountEmployees(idEmployees) || checkAllEmployeesId(listEmployee, idEmployees) || (boolean) getMeasuremantInstrument.get(0)) {
                return Arrays.asList(false, null);
            }
            if (checkForDuplicatesEmployeeId(idEmployees)) {
                log.error(Constants.ERROR_DUPLICATES_EMPLOYEE_ID);
                return Arrays.asList(false, null);
            }
            HeadOfDepartment headOfDepartment;
            headOfDepartment = (HeadOfDepartment) getHeadDepartment.get(1);
            Executor executor;
            executor = headOfDepartment.getExecutor();
            outfit.setId(id);
            outfit.setDateStartWork(dateStartWork);
            outfit.setExecutor(executor);
            outfit.setPlaceWork((PlaceOfWork) getPlaceWorkInOutfit.get(1));
            outfit.setCustomer((Customer) getCustomerOutfit.get(1));
            outfit.setMeansMeasurement((MeansMeasurement) getMeasuremantInstrument.get(1));
            Set<Employee> employeesOutfit = new HashSet<Employee>((List<Employee>)getEmployeesOutfit.get(1));
            outfit.setEmployees(employeesOutfit);
            log.info(outfit);
            log.info(Constants.CREATE_OUTFIT_SUCCESSFUL);
            save(Arrays.asList(outfit));
        }catch (NullPointerException e){
            log.error(Constants.ERROR_CREATE_OUTFIT);
            log.error(e);
            return Arrays.asList(false, null);
        }
        return Arrays.asList(true, Optional.of(outfit));
    }

    public <T> Optional<T> getById(Class className, long id){
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
    public List<Object> editOutfit(long idOutfit, List<Long> idEmployees, long idHeadOfDepartment){
        Session session = null;
        Transaction transaction;
        Outfit updateEntity;
        if(idEmployees.isEmpty()){
            return null;
        }
        try {
            session = getSession();
            List<Outfit> listOutfitFromDb = getListOutfit();
            List<Employee> listEmployeeFromDb = getListEmployee();
            List getEmployeesOutfit = findExistsEmployeeId(idEmployees);
            if ((boolean) findExistsHeadOfDepartmentId(idHeadOfDepartment).get(0) || findExistsOutfitId(idOutfit) || (boolean) getEmployeesOutfit.get(0) || checkIdEmployeesNotNull(idEmployees) || checkOutfitsNotNull(listOutfitFromDb) || checkCountEmployees(idEmployees) || checkAllEmployeesId(listEmployeeFromDb, idEmployees)) {
                return Arrays.asList(false, null);
            }
            updateEntity = (Outfit) getById(Outfit.class, idOutfit).get();
            Set<Employee> employeesOutfit = new HashSet<Employee>((List<Employee>)getEmployeesOutfit.get(1));
            updateEntity.setEmployees(employeesOutfit);
            transaction = session.beginTransaction();
            session.update(updateEntity);
            transaction.commit();
            log.info(Constants.EDIT_OUTFIT_SUCCESSFUL);
        }catch (NullPointerException e){
            log.error(Constants.ERROR_EDIT_OUTFIT);
            log.error(e);
            return Arrays.asList(false, null);
        }finally {
            session.close();
        }
        return Arrays.asList(true, updateEntity);
    }

    @Override
    public boolean deleteOutfit(long idOutfit, long idHeadOfDepartment) {
        Session session = getSession();
        Transaction transaction;
        try {
            List<Outfit> listOutfitFromDb = getListOutfit();
            if ((boolean) findExistsHeadOfDepartmentId(idHeadOfDepartment).get(0) || findExistsOutfitId(idOutfit) || checkOutfitsNotNull(listOutfitFromDb)) {
                return false;
            }
            transaction = session.beginTransaction();
            Outfit deleteEntity = (Outfit) getById(Outfit.class, idOutfit).orElseThrow();
            session.delete(deleteEntity);
            transaction.commit();
            log.info(Constants.DELETE_OUTFIT_SUCCESSFUL);
        } catch (Exception e) {
            log.error(Constants.ERROR_DELETE_OUTFIT);
            log.error(e);
            return false;
        }
        return true;
    }

    @Override
    public void delete(List<Outfit> outfits) {
        outfits.forEach(entity ->{
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            session.close();
        });
    }

    private List<Object> findExistsEmployeeId(List<Long> idEmployee)  {
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<Employee> employees;
        List getDuplicatesEmployee = null;
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            getDuplicatesEmployee = checkForDuplicatesEmployeeId(idEmployee, employees);
            if ((boolean) getDuplicatesEmployee.get(0)) {
                log.error(Constants.ERROR_EMPLOYEE_ID_NOT_FOUND);
                return Arrays.asList(true, getDuplicatesEmployee.get(1));
            }
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Arrays.asList(false, getDuplicatesEmployee.get(1));
    }

    private List<Object> findExistsHeadOfDepartmentId(long idHeadOfDepartment) {
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<HeadOfDepartment> listHeadOfDepartment;
        List checkForDuplicatesHeadDepartmentId = null;
        try {
            listHeadOfDepartment = session.createQuery("FROM HeadOfDepartment").list();
            checkForDuplicatesHeadDepartmentId = checkForDuplicatesHeadOfDepartmentId(listHeadOfDepartment, idHeadOfDepartment);
            if ((boolean) checkForDuplicatesHeadDepartmentId.get(0)) {
                log.error(Constants.ERROR_HEAD_DEPARTMENT_ID_NOT_FOUND);
                return Arrays.asList(true, checkForDuplicatesHeadDepartmentId.get(1));
            }
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Arrays.asList(false, checkForDuplicatesHeadDepartmentId.get(1));
    }

    private boolean findExistsOutfitId(long idOutfit) {
        List<Outfit> listOutfit = getListOutfit();
        if (checkForDuplicatesOutfitId(listOutfit, idOutfit)) {
            log.error(Constants.ERROR_OUTFIT_ID_NOT_FOUND);
            return true;
        }
        return false;
    }

    private boolean checkForDuplicatesOutfitId(List<Outfit> list, long id) {
        List<Outfit> dublicateId = list.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        return dublicateId.isEmpty();
    }

    private List<Object> findPlaceWorkId(long idPlaceWork) {
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<PlaceOfWork> listPlaceWork;
        List placeWorkInOutfit = null;
        try {
            transaction = session.beginTransaction();
            listPlaceWork = session.createQuery("FROM PlaceOfWork").list();
            placeWorkInOutfit = checkForDuplicatesPlaceOfWorkId(listPlaceWork, idPlaceWork);
            if ((boolean) placeWorkInOutfit.get(0)) {
                log.error(Constants.ERROR_PLACE_OF_WORK_ID_NOT_FOUND);
                return Arrays.asList(true, placeWorkInOutfit.get(1));
            }
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Arrays.asList(false, placeWorkInOutfit.get(1));
    }

    private boolean checkOutfitsNotNull(List<Outfit> listOutfit) {
        try {
            return listOutfit.isEmpty();
        }catch (NullPointerException e){
            log.error(Constants.ERROR_NOT_DATA_OUTFIT);
            return true;
        }
    }

    private List<Object> findExistsCustomerId(long idCustomer) {
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<Customer> listCustomer;
        List customerInOutfit = null;
        try {
            transaction = session.beginTransaction();
            listCustomer = session.createQuery("FROM Customer").list();
            customerInOutfit = checkForDuplicatesCustomerId(listCustomer, idCustomer);
            if ((boolean) customerInOutfit.get(0)) {
                log.error(Constants.ERROR_CUSTOMER_ID_NOT_FOUND);
                return Arrays.asList(true, customerInOutfit.get(1));
            }
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return Arrays.asList(false, customerInOutfit.get(1));
    }

    public List<Outfit> getListOutfit(){
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<Outfit> listOutfit = null;
        try{
            transaction = session.beginTransaction();
            listOutfit = session.createQuery("FROM Outfit").list();
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return listOutfit;
    }

    private List<Employee> getListEmployee(){
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<Employee> listEmployee = null;
        try{
            transaction = session.beginTransaction();
            listEmployee = session.createQuery("FROM Employee").list();
        }catch (Exception e){
            log.error(e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return listEmployee;
    }

    private List<Object> checkIdTypeMeasurementInstrument(long id) {
        Session session;
        session = getSession();
        MeansMeasurement meansOfMeasurement;
        try {
            meansOfMeasurement = (MeansMeasurement) getById(MeansMeasurement.class, id).get();
        }catch (NullPointerException e){
            log.error(e);
            return Arrays.asList(true, null);
        }finally {
            session.close();
        }
        return Arrays.asList(false, meansOfMeasurement);
    }


    private List<Object> checkForDuplicatesEmployeeId(List<Long> idEmployees, List<Employee> listEmployee) {
        List<Employee> dublicateId = listEmployee.stream()
                .filter(i -> Collections.frequency(idEmployees, i.getId()) > 0)
                .collect(Collectors.toList());
        boolean flag = dublicateId.isEmpty();
        return Arrays.asList(flag, dublicateId);
    }

    private boolean checkForDuplicatesEmployeeId(List<Long> idEmployees) {
        List<Long> dublicateId = idEmployees.stream()
                .filter(i -> Collections.frequency(idEmployees, i) > 1)
                .collect(Collectors.toList());
        return !dublicateId.isEmpty();
    }

    private List<Object> checkForDuplicatesHeadOfDepartmentId(List<HeadOfDepartment> list, long id) {
        List<HeadOfDepartment> dublicateId = list.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        boolean flag = dublicateId.isEmpty();
        return Arrays.asList(flag, dublicateId.get(0));
    }


    private List<Object> checkForDuplicatesPlaceOfWorkId(List<PlaceOfWork> list, long id) {
        List<PlaceOfWork> dublicateId = list.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        boolean flag = dublicateId.isEmpty();
        return Arrays.asList(flag, dublicateId.get(0));
    }

    private List<Object> checkForDuplicatesCustomerId(List<Customer> list, long id) {
        List<Customer> dublicateId = list.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        boolean flag = dublicateId.isEmpty();
        return Arrays.asList(flag, dublicateId.get(0));
    }

    private boolean checkCountEmployees(List<Long> idEmployees) {
        if ((idEmployees.size() < 2) || (idEmployees.size() > 3)) {
            log.error(Constants.ERROR_COUNT_EMPLOYEES);
            return true;
        }
        return false;
    }

    public boolean checkIdEmployeesNotNull(List<Long> idEmployees) {
        try{
            return idEmployees.isEmpty();
        }catch (NullPointerException e){
            log.error(Constants.ERROR_EMPLOYEE_ID_NOT_FOUND);
            return true;
        }
    }

    private boolean checkAllEmployeesId(List<Employee> listEmployee, List<Long> idEmployees) {
        List<Employee> dublicateId = null;
        try {
            dublicateId = listEmployee.stream()
                    .filter(i -> Collections.frequency(idEmployees, i.getId()) > 0)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e);
        }
        if (dublicateId.size() == idEmployees.size()) {
            return false;
        }
        log.error(Constants.ERROR_EMPLOYEE_ID_NOT_FOUND);
        return true;
    }

    public <T> List<Long> save(List<T> records){
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
        }catch (Exception e){
            log.error(e);
            return null;
        }finally {
            session.close();
        }
        return ids;
    }

    public static List<Long> stringToList(String listId){
        try{
            return Stream.of(listId.split(Constants.DELIMITER))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error(e);
            log.error(Constants.ERROR_CONVERT_STRING_TO_LIST);
            return null;
        }
    }

    public List<Customer> getAllCustomerHql() {
        Session session = getSession();
        Query query = session.createQuery("FROM " + Constants.CUSTOMER_ENTITY);
        List<Customer> data = query.getResultList();
        session.close();
        return data;
    }


    public List<Customer> getAllCustomerSql() {
        Session session = getSession();
        NativeQuery<Customer> sqlQuery = session.createSQLQuery("SELECT * FROM " + Constants.CUSTOMER);
        List<Customer> customers = sqlQuery.getResultList();
        session.close();
        return customers;
    }

    public List<Customer> getAllCustomerCriteria() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        criteria.from(Customer.class);
        List<Customer> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    public BigInteger getPlaceOfWorkCountNative() {
        Session session = getSession();
        String query = String.format(Constants.GET_COUNT, Constants.PLACE_OF_WORK);
        BigInteger count = (BigInteger) session.createSQLQuery(query).list().get(0);
        session.close();
        return count;
    }

    public Long getPlaceOfWorkCountHQL() {
        Session session = getSession();
        String query = String.format(Constants.GET_COUNT, Constants.PLACE_OF_WORK_ENTITY);
        Long count = (Long) session.createQuery(query).list().get(0);
        session.close();
        return count;
    }

    public Long getPlaceOfWorkCountCriteria() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(PlaceOfWork.class);
        criteria.setProjection(Projections.count("id"));
        Long count = (Long) criteria.list().get(0);
        session.close();
        return count;
    }

    @Override
    public void deleteAll() {
        Constants.ENTITIES_LAB5.forEach(entity ->{
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            String query = String.format(Constants.DELETE_ENTITY, entity);
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            session.close();
        });
    }

    @Override
    public void initDb(){
        List<Outfit> outfits = new ArrayList<>();
        outfits = getListOutfit();
        if(!outfits.isEmpty()){
            delete(outfits);
        }
        deleteAll();

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

        save(headOfDepartments);

        headOfDepartment = (HeadOfDepartment) getById(HeadOfDepartment.class, 40).get();
        headOfDepartment1 = (HeadOfDepartment) getById(HeadOfDepartment.class, 41).get();
        headOfDepartment2 = (HeadOfDepartment) getById(HeadOfDepartment.class, 42).get();
        headOfDepartment3 = (HeadOfDepartment) getById(HeadOfDepartment.class, 43).get();

        headOfDepartment4 = (HeadOfDepartment) getById(HeadOfDepartment.class, 44).get();
        headOfDepartment5 = (HeadOfDepartment) getById(HeadOfDepartment.class, 45).get();
        headOfDepartment6 = (HeadOfDepartment) getById(HeadOfDepartment.class, 46).get();
        headOfDepartment7 = (HeadOfDepartment) getById(HeadOfDepartment.class, 47).get();

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
        save(executors);

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

        save(customers);
        save(placeOfWorks);
        save(employees);
        save(meansMeasurements);

        List<Long> idEmployees = stringToList("20,21");
        createOutfit(new Date(), 1, 40, 10, 30, idEmployees, 1).get(1);

        List<Long> idEmployee = stringToList("22,23");
        createOutfit(new Date(), 2, 41, 11, 31, idEmployee, 2).get(1);
    }

    public Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}

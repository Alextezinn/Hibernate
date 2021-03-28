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
import ru.sfedu.hiber.lab3.strategy3.model.Account2;
import ru.sfedu.hiber.lab4.api.CollectionSetProvider;
import ru.sfedu.hiber.lab4.models.Outfit;
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
                                     long idTypeMeasurementInstrument, List<Long> idEmployees) throws NoSuchElementException {
        MyOutfit outfit = new MyOutfit();
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
        MyOutfit updateEntity;
        if(idEmployees.isEmpty()){
            return null;
        }
        try {
            session = getSession();
            List<MyOutfit> listOutfitFromDb = getListOutfit();
            List<Employee> listEmployeeFromDb = getListEmployee();
            List getEmployeesOutfit = findExistsEmployeeId(idEmployees);
            if ((boolean) findExistsHeadOfDepartmentId(idHeadOfDepartment).get(0) || findExistsOutfitId(idOutfit) || (boolean) getEmployeesOutfit.get(0) || checkIdEmployeesNotNull(idEmployees) || checkOutfitsNotNull(listOutfitFromDb) || checkCountEmployees(idEmployees) || checkAllEmployeesId(listEmployeeFromDb, idEmployees)) {
                return Arrays.asList(false, null);
            }
            updateEntity = (MyOutfit) getById(MyOutfit.class, idOutfit).get();
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
            List<MyOutfit> listOutfitFromDb = getListOutfit();
            if ((boolean) findExistsHeadOfDepartmentId(idHeadOfDepartment).get(0) || findExistsOutfitId(idOutfit) || checkOutfitsNotNull(listOutfitFromDb)) {
                return false;
            }
            transaction = session.beginTransaction();
            MyOutfit deleteEntity = (MyOutfit) getById(MyOutfit.class, idOutfit).orElseThrow();
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
        List<MyOutfit> listOutfit = getListOutfit();
        if (checkForDuplicatesOutfitId(listOutfit, idOutfit)) {
            log.error(Constants.ERROR_OUTFIT_ID_NOT_FOUND);
            return true;
        }
        return false;
    }

    private boolean checkForDuplicatesOutfitId(List<MyOutfit> list, long id) {
        List<MyOutfit> dublicateId = list.stream()
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

    private boolean checkOutfitsNotNull(List<MyOutfit> listOutfit) {
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

    private List<MyOutfit> getListOutfit(){
        Session session;
        Transaction transaction = null;
        session = getSession();
        List<MyOutfit> listOutfit = null;
        try{
            transaction = session.beginTransaction();
            listOutfit = session.createQuery("FROM MyOutfit").list();
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

    public Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}

package ru.sfedu.hiber;

public class Constants {
    public static final String SQL_ALL_SCHEMAS = "SELECT schema_name FROM information_schema.schemata";
    //таблицы
    public static final String SQL_ALL_TABLES = "SELECT table_name FROM information_schema.tables";
    //типы таблиц
    public static final String SQL_ALL_TABLES_TYPE = "SELECT table_type FROM information_schema.tables";
    //список всех привилегий на таблицы
    public static final String SQL_ALL_ROLE_TABLES_GRANTS = "SELECT * FROM information_schema.role_table_grants";

    public static final String DELIMITER = ",";
    public static final String ERROR_CONVERT_STRING_TO_LIST = "Error convert string to list";
    public static final String ERROR_EMPLOYEE_ID_NOT_FOUND = "Error employee id not found";
    public static final String ERROR_CREATE_OUTFIT = "Outfit create error";
    public static final String ERROR_CUSTOMER_ID_NOT_FOUND = "Error customer id not found";
    public static final String ERROR_PLACE_OF_WORK_ID_NOT_FOUND = "Error place of work id not found";
    public static final String ERROR_HEAD_DEPARTMENT_ID_NOT_FOUND = "Error head department id not found";
    public static final String ERROR_COUNT_EMPLOYEES = "Error count employees should be 2 or 3 employees";
    public static final String ERROR_DUPLICATES_EMPLOYEE_ID = "Error duplicates employee id";
    public static final String ERROR_OUTFIT_ID_NOT_FOUND = "Error outfit id not found";
    public static final String ERROR_NOT_DATA_OUTFIT = "No data about outfit";
    public static final String ERROR_EDIT_OUTFIT = "Outfit edit error";
    public static final String ERROR_DELETE_OUTFIT = "Outfit delete error";
    public static final String COMMAND_ERROR = "Incorrect command entry";
    public static final String ERROR_CHOOSE_LAB = "Error choose lab";

    public static final String CUSTOMER = "customer";
    public static final String CUSTOMER_ENTITY = "Customer";
    public static final String PLACE_OF_WORK_ENTITY = "PlaceOfWork";

    public static final String PLACE_OF_WORK = "place_of_work";
    public static final String GET_COUNT = "select count(*) from %s";



    public static final String EDIT_OUTFIT_SUCCESSFUL = "Outfit edited successful";
    public static final String DELETE_OUTFIT_SUCCESSFUL = "Outfit deleted successful";
    public static final String CREATE_OUTFIT_SUCCESSFUL = "Outfit created successful";
    public static final String DELETE_SUCCESSFUL = "Delete successful";

    public static final String LAB1 = "LAB1";
    public static final String LAB2 = "LAB2";
    public static final String LAB3 = "LAB3";
    public static final String LAB4 = "LAB4";
    public static final String LAB5 = "LAB5";

    //LAB1
    public static final String GET_SCHEMAS = "GET_SCHEMAS";
    public static final String GET_TABLES = "GET_TABLES";
    public static final String GET_ROLE_TABLES = "GET_ROLE_TABLES";
    public static final String GET_TABLES_TYPE = "GET_TABLES_TYPE";

    //LAB2
    public static final String SAVE = "SAVE";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String GET_BY_ID = "GET_BY_ID";

    //LAB3
    public static final String STRATEGY1 = "STRATEGY1";
    public static final String STRATEGY2 = "STRATEGY2";
    public static final String STRATEGY3 = "STRATEGY3";
    public static final String STRATEGY4 = "STRATEGY4";

    //LAB4
    public static final String SET = "SET";
    public static final String MAP = "MAP";
    public static final String LIST = "LIST";

}

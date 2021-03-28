package ru.sfedu.hiber.lab1.api;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class HibernateMetaDataProviderTest {

    @Test
    public void getListSchemasSuccess() throws IOException {
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListSchemas();
        assertNotNull(result);
    }

    @Test
    public void getListSchemasFail() throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListSchemas();
        assertNotEquals(result, null);
    }

    @Test
    public void getListTablesSuccess() throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListTables();
        assertNotNull(result);
    }

    @Test
    public void getListTablesFail() throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListTables();
        assertNotEquals(result, null);
    }

    @Test
    public void getListTablesTypeSuccess()throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListTablesType();
        assertNotNull(result);
    }

    @Test
    public void getListTablesTypeFail()throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListTablesType();
        assertNotEquals(result, null);
    }

    @Test
    public void getListRoleTablesGrantsSuccess()throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListRoleTablesGrants();
        assertNotNull(result);
    }

    @Test
    public void getListRoleTablesGrantsFail()throws IOException{
        HibernateMetaDataProvider instance = new HibernateMetaDataProvider();
        List result = instance.getListRoleTablesGrants();
        assertNotEquals(result, null);
    }
}
package ru.sfedu.hiber.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hiber.lab2.models.OtherEntity;
import ru.sfedu.hiber.lab2.models.TestEntity;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestEntityProviderTest {

    private final static Logger log = LogManager.getLogger(TestEntityProviderTest.class);

    @Before
    public void initDb()throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        instance.deleteAll();
        TestEntity entity = new TestEntity();
        OtherEntity otherEntity = new OtherEntity();
        entity.setId(1);
        entity.setName("Name");
        entity.setDescription("tesut");
        entity.setDateCreated(new Date());
        entity.setCheck(true);
        otherEntity.setCount(10.96);
        otherEntity.setOther("Named");
        otherEntity.setComplicationDate(new Date());
        entity.setOtherEntity(otherEntity);
        instance.save(entity);

        TestEntity entity1 = new TestEntity();
        OtherEntity otherEntity1 = new OtherEntity();
        entity1.setId(2);
        entity1.setName("Name");
        entity1.setDescription("tesut");
        entity1.setDateCreated(new Date());
        entity1.setCheck(true);
        otherEntity1.setCount(16.96);
        otherEntity1.setOther("Namei");
        otherEntity1.setComplicationDate(new Date());
        entity1.setOtherEntity(otherEntity1);
        instance.save(entity1);
    }


    @Test
    public void getByIdSuccess() throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        Optional<TestEntity> entity = instance.getById(TestEntity.class, (long)1);
        log.info(entity.get());
        assertEquals((long)1, entity.get().getId());
    }

    @Test
    public void getByIdFail() throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        Optional<TestEntity> entity = null;
        try {
            entity = instance.getById(TestEntity.class, (long) 77654567);
            log.info(entity.get());
        }catch (NullPointerException e){
            log.info(e);
        }
        assertNull(entity);
    }

    @Test
    public void saveSuccess() throws IOException {
        TestEntity entity = new TestEntity();
        OtherEntity otherEntity = new OtherEntity();
        entity.setId(3);
        entity.setName("Name1");
        entity.setDescription("test");
        entity.setDateCreated(new Date());
        entity.setCheck(true);
        otherEntity.setCount(10.76);
        otherEntity.setOther("Name2");
        otherEntity.setComplicationDate(new Date());
        entity.setOtherEntity(otherEntity);
        ITestEntityProvider instance = new TestEntityProvider();
        Long result = instance.save(entity);
        Optional<TestEntity> entity1 = instance.getById(TestEntity.class, result);
        log.info(entity1.get());
        assertEquals(entity, entity1.get());
    }

    @Test
    public void saveFail() throws IOException{
        TestEntity entity = new TestEntity();
        OtherEntity otherEntity = new OtherEntity();
        entity.setId(12345);
        entity.setName("Name10");
        entity.setDescription("test");
        entity.setDateCreated(new Date());
        entity.setCheck(true);
        otherEntity.setCount(10.76);
        otherEntity.setOther("Name2");
        otherEntity.setComplicationDate(new Date());
        entity.setOtherEntity(otherEntity);
        ITestEntityProvider instance = new TestEntityProvider();
        Long result = instance.save(entity);
        assertEquals(Optional.of((long) 0), Optional.of((result)));
    }

    @Test
    public void updateSuccess() throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        TestEntity entity = instance.getById(TestEntity.class,(long)1).get();
        entity.setName("NewName");
        TestEntity updateEntity = instance.update(TestEntity.class, (long) 1, "NewName").get();
        assertEquals(entity, updateEntity);
    }

    @Test
    public void updateFail()throws IOException{
        TestEntity updateEntity = null;
        ITestEntityProvider instance = new TestEntityProvider();
        TestEntity entity = instance.getById(TestEntity.class,(long)1).get();
        entity.setName("NewName2");
        try {
            updateEntity = instance.update(TestEntity.class, (long) 4567, "NewNames").get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNotEquals(entity, updateEntity);
    }

    @Test
    public void deleteSuccess()throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        instance.delete(TestEntity.class,(long)2);
        TestEntity entity = null;
        try{
            entity = instance.getById(TestEntity.class,(long)2).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(entity);
    }

    @Test
    public void deleteFail()throws IOException{
        ITestEntityProvider instance = new TestEntityProvider();
        boolean status;
        try {
            status = instance.delete(TestEntity.class, (long) 123);
        }catch (NullPointerException e){
            status = false;
            log.error(e);
        }
        assertFalse(status);
    }

}
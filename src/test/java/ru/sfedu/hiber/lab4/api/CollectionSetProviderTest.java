package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.hiber.lab4.models.MeansOfMeasurement;
import ru.sfedu.hiber.lab4.models.Outfit;

import java.util.*;

import static org.junit.Assert.*;

public class CollectionSetProviderTest {
    private final static Logger log = LogManager.getLogger(CollectionSetProviderTest.class);

    @Test
    public void getByIdSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit outfit;
        outfit = (Outfit) provider.getById(Outfit.class, 1).get();
        assertNotNull(outfit);
    }

    @Test
    public void getByIdFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit outfit = null;
        try {
            outfit = (Outfit) provider.getById(Outfit.class, 187).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void saveSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        List<Long> ids;

        Outfit outfit = new Outfit();
        outfit.setId(5);
        outfit.setName("Bob");
        ids = provider.save(Arrays.asList(outfit));
        assertNotNull(ids);

//        MeansOfMeasurement instrument1 = new MeansOfMeasurement();
//        instrument1.setId(4);
//        instrument1.setMeasurementError(0.001);
//        instrument1.setNameMeansOfMeasurement("instrument5");
//        instrument1.setIdOutfit(2);
//        ids = provider.save(Arrays.asList(instrument1));
//        assertNotNull(ids);
    }

    @Test
    public void saveFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        List<Long> ids = null;

        Outfit outfit = new Outfit();
        outfit.setId(1);
        outfit.setName("Bob");
        try {
            ids = provider.save(Arrays.asList(outfit));
        }catch (AssertionError e){
            log.error(e);
        }
        assertNull(ids);
    }

    @Test
    public void updateSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit outfit;
        outfit = (Outfit) provider.update(Outfit.class, 1, "Ivan").get();
        assertNotNull(outfit);
    }

    @Test
    public void updateFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit outfit = null;
        try {
            outfit = (Outfit) provider.update(Outfit.class, 1, null).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void deleteSuccess(){
        CollectionSetProvider provider = new CollectionSetProvider();
        boolean flag = provider.delete(Outfit.class, 4);
        assertTrue(flag);
    }

    @Test
    public void deleteFail(){
        CollectionSetProvider provider = new CollectionSetProvider();
        boolean flag = provider.delete(Outfit.class, 1234);
        assertFalse(flag);
    }

}
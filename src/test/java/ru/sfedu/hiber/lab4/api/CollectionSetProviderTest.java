package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hiber.lab4.models.MeansOfMeasurement3;
import ru.sfedu.hiber.lab4.models.Outfit3;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CollectionSetProviderTest {
    private final static Logger log = LogManager.getLogger(CollectionSetProviderTest.class);

    @Before
    public void initDb() throws IOException {
        CollectionSetProvider provider = new CollectionSetProvider();
        provider.deleteAll();
        Outfit3 outfit3 = new Outfit3();
        outfit3.setId(1);
        outfit3.setName("Bob");

        MeansOfMeasurement3 instrument1 = new MeansOfMeasurement3();
        instrument1.setId(1);
        instrument1.setMeasurementError(0.0001);
        instrument1.setNameMeansOfMeasurement("Instrument12");
        instrument1.setIdOutfit(1);
        provider.save(Arrays.asList(outfit3));
        provider.save(Arrays.asList(instrument1));


        Outfit3 newOutfit3 = new Outfit3();
        newOutfit3.setId(2);
        newOutfit3.setName("Bob");

        MeansOfMeasurement3 instrument2 = new MeansOfMeasurement3();
        instrument2.setId(2);
        instrument2.setMeasurementError(0.0001);
        instrument2.setNameMeansOfMeasurement("Instrument11");
        instrument2.setIdOutfit(2);
        provider.save(Arrays.asList(newOutfit3));
        provider.save(Arrays.asList(instrument2));

    }

    @Test
    public void getByIdSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit3 outfit3;
        outfit3 = (Outfit3) provider.getById(Outfit3.class, 1).get();
        assertNotNull(outfit3);
    }

    @Test
    public void getByIdFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit3 outfit3 = null;
        try {
            outfit3 = (Outfit3) provider.getById(Outfit3.class, 187).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit3);
    }

    @Test
    public void saveSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        List<Long> ids;

        Outfit3 outfit3 = new Outfit3();
        outfit3.setId(3);
        outfit3.setName("Bob");
        ids = provider.save(Arrays.asList(outfit3));
        assertNotNull(ids);
    }

    @Test
    public void saveFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        List<Long> ids = null;

        Outfit3 outfit3 = new Outfit3();
        outfit3.setId(1);
        outfit3.setName("Bob");
        try {
            ids = provider.save(Arrays.asList(outfit3));
        }catch (AssertionError e){
            log.error(e);
        }
        assertNull(ids);
    }

    @Test
    public void updateSuccess() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit3 outfit3;
        outfit3 = (Outfit3) provider.update(Outfit3.class, 1, "Ivan").get();
        assertNotNull(outfit3);
    }

    @Test
    public void updateFail() {
        CollectionSetProvider provider = new CollectionSetProvider();
        Outfit3 outfit3 = null;
        try {
            outfit3 = (Outfit3) provider.update(Outfit3.class, 1, null).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit3);
    }

    @Test
    public void deleteSuccess(){
        CollectionSetProvider provider = new CollectionSetProvider();
        boolean flag = provider.delete(Outfit3.class, 2);
        assertTrue(flag);
    }

    @Test
    public void deleteFail(){
        CollectionSetProvider provider = new CollectionSetProvider();
        boolean flag = provider.delete(Outfit3.class, 1234);
        assertFalse(flag);
    }

}
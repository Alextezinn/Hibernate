package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.hiber.lab4.models.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionMapProviderTest {
    private final static Logger log = LogManager.getLogger(CollectionMapProviderTest.class);

    @Test
    public void saveSuccess() {
        CollectionMapProvider provider = new CollectionMapProvider();
        List<Long> ids;

        Outfit2 outfit = new Outfit2();
        outfit.setId(3L);
        outfit.setName("Liza");
        ids = provider.save(Arrays.asList(outfit));
        assertNotNull(ids);

        MeansOfMeasurement2 instrument1 = new MeansOfMeasurement2();
        instrument1.setId(3L);
        instrument1.setMeasurementError(0.001);
        instrument1.setNameMeansOfMeasurement("instrument3");
        instrument1.setIdOutfit(3L);
        ids = provider.save(Arrays.asList(instrument1));
        assertNotNull(ids);
    }

    @Test
    public void saveFail() {
        CollectionMapProvider provider = new CollectionMapProvider();
        List<Long> ids = null;

        Outfit2 outfit = new Outfit2();
        outfit.setId(1L);
        outfit.setName("Bob");
        try {
            ids = provider.save(Arrays.asList(outfit));
        }catch (AssertionError e){
            log.error(e);
        }
        assertNull(ids);
    }

    @Test
    public void getByIdSuccess(){
        CollectionMapProvider provider = new CollectionMapProvider();
        Outfit2 outfit;
        outfit = (Outfit2) provider.getById(Outfit2.class, 1).get();
        log.info(outfit);
        assertNotNull(outfit);
    }

    @Test
    public void getByIdFail(){
        CollectionMapProvider provider = new CollectionMapProvider();
        Outfit2 outfit = null;
        try {
            outfit = (Outfit2) provider.getById(Outfit2.class, 187).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void updateOutfit2Success(){
        CollectionMapProvider provider = new CollectionMapProvider();
        Outfit2 outfit;
        outfit = (Outfit2) provider.updateOutfit2(Outfit2.class, 1, "Ivan1").get();
        assertNotNull(outfit);
    }

    @Test
    public void updateOutfit2Fail(){
        CollectionMapProvider provider = new CollectionMapProvider();
        Outfit2 outfit = null;
        try {
            outfit = (Outfit2) provider.updateOutfit2(Outfit2.class, 1, null).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void deleteSuccess(){
        CollectionMapProvider provider = new CollectionMapProvider();
        boolean flag = provider.delete(Outfit2.class, 2);
        assertTrue(flag);
    }

    @Test
    public void deleteFail(){
        CollectionMapProvider provider = new CollectionMapProvider();
        boolean flag = provider.delete(Outfit2.class, 1234);
        assertFalse(flag);
    }
}
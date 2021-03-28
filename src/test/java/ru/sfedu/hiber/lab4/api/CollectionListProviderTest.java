package ru.sfedu.hiber.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.hiber.lab4.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionListProviderTest {
    private final static Logger log = LogManager.getLogger(CollectionListProviderTest.class);

    @Test
    public void saveSuccess() {
        CollectionListProvider provider = new CollectionListProvider();
        List<Long> ids;

        Outfit1 outfit = new Outfit1();
        outfit.setId(3L);
        outfit.setName("Bob");
        List<String> instruments = new ArrayList<>();
        instruments.add("instrument1");
        outfit.setInstruments(instruments);
        ids = provider.save(Arrays.asList(outfit));
        assertNotNull(ids);
    }

    @Test
    public void saveFail() {
        CollectionListProvider provider = new CollectionListProvider();
        List<Long> ids = null;

        Outfit1 outfit = new Outfit1();
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
    public void getByIdSuccess() {
        CollectionListProvider provider = new CollectionListProvider();
        Outfit1 outfit;
        outfit = (Outfit1) provider.getById(Outfit1.class, 1).get();
        assertNotNull(outfit);
    }

    @Test
    public void getByIdFail() {
        CollectionListProvider provider = new CollectionListProvider();
        Outfit1 outfit = null;
        try {
            outfit = (Outfit1) provider.getById(Outfit1.class, 187).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void updateOutfit1Success(){
        CollectionListProvider provider = new CollectionListProvider();
        Outfit1 outfit;
        outfit = (Outfit1) provider.updateOutfit1(Outfit1.class, 1, "Ivan1").get();
        assertNotNull(outfit);
    }

    @Test
    public void updateOutfit1Fail(){
        CollectionListProvider provider = new CollectionListProvider();
        Outfit1 outfit = null;
        try {
            outfit = (Outfit1) provider.updateOutfit1(Outfit1.class, 1, null).get();
        }catch (NullPointerException e){
            log.error(e);
        }
        assertNull(outfit);
    }

    @Test
    public void deleteSuccess(){
        CollectionListProvider provider = new CollectionListProvider();
        boolean flag = provider.delete(Outfit1.class, 2);
        assertTrue(flag);
    }

    @Test
    public void deleteFail(){
        CollectionListProvider provider = new CollectionListProvider();
        boolean flag = provider.delete(Outfit1.class, 1234);
        assertFalse(flag);
    }

}
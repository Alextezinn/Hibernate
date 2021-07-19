package ru.sfedu.hiber.lab5.api;

import ru.sfedu.hiber.lab5.models.Outfit;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProvider {
    List<Object> createOutfit(Date dateStartWork, long idCustomer, long idHeadOfDepartment, long idPlaceWork,
                              long idTypeMeasurementInstrument, List<Long> idEmployees, long id);

    List<Object> editOutfit(long idOutfit, List<Long> idEmployees, long idHeadOfDepartment);

    boolean deleteOutfit(long idOutfit, long idHeadOfDepartment);

    <T> Optional<T> getById(Class className, long id);

    void deleteAll();

    void delete(List<Outfit> outfits);

    void initDb();
}

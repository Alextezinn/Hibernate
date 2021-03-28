package ru.sfedu.hiber.lab5.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProvider {
    List<Object> createOutfit(Date dateStartWork, long idCustomer, long idHeadOfDepartment, long idPlaceWork,
                              long idTypeMeasurementInstrument, List<Long> idEmployees);

    List<Object> editOutfit(long idOutfit, List<Long> idEmployees, long idHeadOfDepartment);

    boolean deleteOutfit(long idOutfit, long idHeadOfDepartment);
}

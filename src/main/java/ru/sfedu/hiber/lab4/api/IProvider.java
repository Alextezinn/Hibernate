package ru.sfedu.hiber.lab4.api;

import ru.sfedu.hiber.lab4.models.Outfit3;
import ru.sfedu.hiber.lab4.models.Outfit1;
import ru.sfedu.hiber.lab4.models.Outfit2;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProvider {

    <T> List<Long> save(List<T> records);

    Optional<Outfit3> update(Class entity, long id, String name);

    <T> Optional<T> getById(Class className, long id);

    <T> boolean delete(Class<T> entity, long id);

    Optional<Outfit1> updateOutfit1(Class entity, long id, String name);

    Optional<Outfit2> updateOutfit2(Class entity, long id, String name);

    void deleteAll();

    void initDb() throws IOException;
}

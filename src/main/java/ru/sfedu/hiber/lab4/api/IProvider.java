package ru.sfedu.hiber.lab4.api;

import ru.sfedu.hiber.lab4.models.Outfit;
import ru.sfedu.hiber.lab4.models.Outfit1;
import ru.sfedu.hiber.lab4.models.Outfit2;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProvider {

    <T> List<Long> save(List<T> records);

    Optional<Outfit> update(Class entity, long id, String name);

    <T> Optional<T> getById(Class className, long id);

    <T> boolean delete(Class<T> entity, long id);

    Optional<Outfit1> updateOutfit1(Class entity, long id, String name);

    Optional<Outfit2> updateOutfit2(Class entity, long id, String name);
}

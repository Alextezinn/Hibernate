package ru.sfedu.hiber.lab3.strategy4.api;

import ru.sfedu.hiber.lab3.strategy4.model.CreditAccount3;
import ru.sfedu.hiber.lab3.strategy4.model.DebitAccount3;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProvider {
    List<Long> save(CreditAccount3 creditAccount, DebitAccount3 debitAccount);

    Optional<List> getByAccounts();

    <T> Optional<T> getByTypeAccount(Class<T> entity, long id);

    <T> Optional<T> updateTypeAccount(Class<T> entity, long id, String name);

    <T> boolean deleteTypeAccount(Class<T> entity, long id);

    void initDb() throws IOException;

    void deleteAll();
}

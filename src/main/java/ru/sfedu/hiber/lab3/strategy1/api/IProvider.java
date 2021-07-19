package ru.sfedu.hiber.lab3.strategy1.api;

import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProvider {

    <T> boolean deleteTypeAccount(Class<T> entity, long id);

    <T> Optional<T> updateTypeAccount(Class<T> entity, long id, String name);

    <T> Optional<T> getByTypeAccount(Class<T> entity, long id);

    List<Long> save(CreditAccount creditAccount, DebitAccount debitAccount);

    Optional<List> getByAccounts();

    void initDb() throws IOException;

    void deleteAll();
}

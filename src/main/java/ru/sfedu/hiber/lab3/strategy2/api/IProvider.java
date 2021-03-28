package ru.sfedu.hiber.lab3.strategy2.api;

import ru.sfedu.hiber.lab3.strategy1.model.Account;
import ru.sfedu.hiber.lab3.strategy1.model.CreditAccount;
import ru.sfedu.hiber.lab3.strategy1.model.DebitAccount;
import ru.sfedu.hiber.lab3.strategy2.model.Account1;
import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;

import java.util.List;
import java.util.Optional;

public interface IProvider {

    List<Long> save(CreditAccount1 creditAccount, DebitAccount1 debitAccount);

    Optional<List> getByAccounts();

    public <T> Optional<T> getByTypeAccount(Class<T> entity, long id);

    public <T> Optional<T> updateTypeAccount(Class<T> entity, long id, String name);

    public <T> boolean deleteTypeAccount(Class<T> entity, long id);

}

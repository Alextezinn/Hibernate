package ru.sfedu.hiber.lab3.strategy3.api;

import ru.sfedu.hiber.lab3.strategy2.model.CreditAccount1;
import ru.sfedu.hiber.lab3.strategy2.model.DebitAccount1;
import ru.sfedu.hiber.lab3.strategy3.model.CreditAccount2;
import ru.sfedu.hiber.lab3.strategy3.model.DebitAccount2;

import java.util.List;
import java.util.Optional;

public interface IProvider {


    Optional<List> getByAccounts();

    <T> Optional<T> updateTypeAccount(long id, String name);

    <T> boolean deleteTypeAccount(long id);

    List<Long> save(CreditAccount2 creditAccount, DebitAccount2 debitAccount);

    <T> Optional<T> getByTypeAccount(long id);
}

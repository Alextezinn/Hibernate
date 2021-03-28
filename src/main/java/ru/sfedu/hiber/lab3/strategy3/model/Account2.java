package ru.sfedu.hiber.lab3.strategy3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="account2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("null")
public abstract class Account2 implements Serializable {

    public Account2() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String owner;

    @Column
    private BigDecimal balance;

    @Column
    private BigDecimal interestRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account2 account2 = (Account2) o;
        return id.equals(account2.id) && owner.equals(account2.owner) && balance.equals(account2.balance) && interestRate.equals(account2.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, balance, interestRate);
    }

    @Override
    public String toString() {
        return "Account2{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
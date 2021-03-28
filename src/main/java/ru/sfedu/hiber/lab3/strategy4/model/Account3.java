package ru.sfedu.hiber.lab3.strategy4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="account3")
@Inheritance(strategy = InheritanceType.JOINED)
public  class Account3 implements Serializable {

    public Account3() {
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
        Account3 account3 = (Account3) o;
        return id.equals(account3.id) && owner.equals(account3.owner) && balance.equals(account3.balance) && interestRate.equals(account3.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, balance, interestRate);
    }

    @Override
    public String toString() {
        return "Account3{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
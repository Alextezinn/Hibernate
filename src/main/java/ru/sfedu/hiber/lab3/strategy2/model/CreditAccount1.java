package ru.sfedu.hiber.lab3.strategy2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="credit_account1")
public class CreditAccount1 extends Account1 implements Serializable {

    public CreditAccount1() {
    }


    @Column
    private BigDecimal creditLimit;

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditAccount1 that = (CreditAccount1) o;
        return Objects.equals(creditLimit, that.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditLimit);
    }

    @Override
    public String toString() {
        return "CreditAccount1{" +
                "creditLimit=" + creditLimit +
                '}';
    }
}

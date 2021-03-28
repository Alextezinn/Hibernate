package ru.sfedu.hiber.lab3.strategy4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="credit_account3")
@PrimaryKeyJoinColumn(name = "account_id")
public class CreditAccount3 extends Account3 implements Serializable {

    public CreditAccount3() {
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
        CreditAccount3 that = (CreditAccount3) o;
        return creditLimit.equals(that.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditLimit);
    }

    @Override
    public String toString() {
        return "CreditAccount3{" +
                "creditLimit=" + creditLimit +
                '}';
    }
}

package ru.sfedu.hiber.lab3.strategy3.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@DiscriminatorValue("Credit")
public class CreditAccount2 extends Account2 implements Serializable {

    public CreditAccount2() {
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
        CreditAccount2 that = (CreditAccount2) o;
        return creditLimit.equals(that.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditLimit);
    }

    @Override
    public String toString() {
        return "CreditAccount2{" +
                "creditLimit=" + creditLimit +
                '}';
    }
}

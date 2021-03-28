package ru.sfedu.hiber.lab3.strategy3.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@DiscriminatorValue("Debit")
public class DebitAccount2 extends Account2 implements Serializable {

    public DebitAccount2() {
    }

    @Column
    private BigDecimal overdraftFee;

    public BigDecimal getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(BigDecimal overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DebitAccount2 that = (DebitAccount2) o;
        return overdraftFee.equals(that.overdraftFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), overdraftFee);
    }

    @Override
    public String toString() {
        return "DebitAccount2{" +
                "overdraftFee=" + overdraftFee +
                '}';
    }
}

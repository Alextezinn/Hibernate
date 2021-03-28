package ru.sfedu.hiber.lab3.strategy4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="debit_account3")
@PrimaryKeyJoinColumn(name = "account_id")
public class DebitAccount3 extends Account3 implements Serializable {

    public DebitAccount3() {
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
        DebitAccount3 that = (DebitAccount3) o;
        return overdraftFee.equals(that.overdraftFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), overdraftFee);
    }

    @Override
    public String toString() {
        return "DebitAccount3{" +
                "overdraftFee=" + overdraftFee +
                '}';
    }
}
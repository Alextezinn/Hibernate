package ru.sfedu.hiber.lab2.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class OtherEntity implements Serializable {
    public OtherEntity(){
    }

    @Column
    private double count;

    @Column
    private String other;

    @Column
    private Date complicationDate;


    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getComplicationDate() {
        return complicationDate;
    }

    public void setComplicationDate(Date complicationDate) {
        this.complicationDate = complicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherEntity that = (OtherEntity) o;
        return Double.compare(that.count, count) == 0 && Objects.equals(other, that.other) && Objects.equals(complicationDate, that.complicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, other, complicationDate);
    }

    @Override
    public String toString() {
        return "OtherEntity{" +
                "count=" + count +
                ", other='" + other + '\'' +
                ", complicationDate=" + complicationDate +
                '}';
    }
}

package ru.sfedu.hiber.lab5.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="measurement_instrument")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("null")
public class MeansMeasurement implements Serializable {

    public MeansMeasurement() {
    }

    @Id
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private double measurementError;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeasurementError() {
        return measurementError;
    }

    public void setMeasurementError(double measurementError) {
        this.measurementError = measurementError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeansMeasurement that = (MeansMeasurement) o;
        return id == that.id && Double.compare(that.measurementError, measurementError) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measurementError);
    }

    @Override
    public String toString() {
        return "MeansMeasurement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurementError=" + measurementError +
                '}';
    }
}

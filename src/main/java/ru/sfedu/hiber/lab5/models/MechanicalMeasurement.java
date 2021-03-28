package ru.sfedu.hiber.lab5.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;


@Entity
@DiscriminatorValue("Mechanical")
public class MechanicalMeasurement extends MeansMeasurement implements Serializable {

    public MechanicalMeasurement() {
    }

    @Column
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicalMeasurement that = (MechanicalMeasurement) o;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public String toString(){
        return "MechanicalMeasurement{id=" + getId() + ", name=" + getName() +
                " ,measurementError=" + getMeasurementError() + ", model=" + getModel() + "}";
    }
}

package ru.sfedu.hiber.lab5.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorValue("Electrical")
public class ElectricalMeasurement extends MeansMeasurement implements Serializable {

    public ElectricalMeasurement() {
    }

    @Column
    private String model;

    @Column
    private double power;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectricalMeasurement that = (ElectricalMeasurement) o;
        return Double.compare(that.power, power) == 0 && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, power);
    }

    @Override
    public String toString(){
        return "ElectricalMeasurement{id=" + getId() + ", name=" + getName() +
                ", measurementError=" + getMeasurementError() + " , model=" + model +
                ", power=" + power + "}";
    }
}

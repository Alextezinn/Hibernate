package ru.sfedu.hiber.lab4.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MEANSOFMEASUREMENT")
public class MeansOfMeasurement implements Serializable {
    public MeansOfMeasurement() {
    }

    @Id
    @Column
    private long id;

    @Column
    private String nameMeansOfMeasurement;

    @Column
    private double measurementError;

    @Column(name = "id_outfit")
    private long idOutfit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMeasurementError() {
        return measurementError;
    }

    public void setMeasurementError(double measurementError) {
        this.measurementError = measurementError;
    }

    public String getNameMeansOfMeasurement() {
        return nameMeansOfMeasurement;
    }

    public void setNameMeansOfMeasurement(String nameMeansOfMeasurement) {
        this.nameMeansOfMeasurement = nameMeansOfMeasurement;
    }

    public long getIdOutfit() {
        return idOutfit;
    }

    public void setIdOutfit(long idOutfit) {
        this.idOutfit = idOutfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeansOfMeasurement that = (MeansOfMeasurement) o;
        return id == that.id && Double.compare(that.measurementError, measurementError) == 0 && idOutfit == that.idOutfit && Objects.equals(nameMeansOfMeasurement, that.nameMeansOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMeansOfMeasurement, measurementError, idOutfit);
    }

    @Override
    public String toString() {
        return "MeansOfMeasurement{" +
                "id=" + id +
                ", nameMeansOfMeasurement='" + nameMeansOfMeasurement + '\'' +
                ", measurementError=" + measurementError +
                ", idOutfit=" + idOutfit +
                '}';
    }
}

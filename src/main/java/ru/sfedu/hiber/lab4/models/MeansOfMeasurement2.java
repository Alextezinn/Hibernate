package ru.sfedu.hiber.lab4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "MEANSOFMEASUREMENT2")
public class MeansOfMeasurement2 implements Serializable {
    public MeansOfMeasurement2(){
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private String nameMeansOfMeasurement;

    @Column
    private double measurementError;

    @Column(name = "outfit2_id")
    private long idOutfit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdOutfit() {
        return idOutfit;
    }

    public void setIdOutfit(long idOutfit) {
        this.idOutfit = idOutfit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeansOfMeasurement2 that = (MeansOfMeasurement2) o;
        return Double.compare(that.measurementError, measurementError) == 0 && idOutfit == that.idOutfit && Objects.equals(id, that.id) && Objects.equals(nameMeansOfMeasurement, that.nameMeansOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMeansOfMeasurement, measurementError, idOutfit);
    }

    @Override
    public String toString() {
        return "MeansOfMeasurement2{" +
                "id=" + id +
                ", nameMeansOfMeasurement='" + nameMeansOfMeasurement + '\'' +
                ", measurementError=" + measurementError +
                ", idOutfit=" + idOutfit +
                '}';
    }
}

package ru.sfedu.hiber.lab4.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MEANSOFMEASUREMENT1")
public class MeansOfMeasurement1 implements Serializable {

    public MeansOfMeasurement1(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column
    private String nameMeansOfMeasurement;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
        MeansOfMeasurement1 that = (MeansOfMeasurement1) o;
        return Objects.equals(nameMeansOfMeasurement, that.nameMeansOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMeansOfMeasurement);
    }

    @Override
    public String toString() {
        return "MeansOfMeasurement1{" +
                "id=" + id +
                ", nameMeansOfMeasurement='" + nameMeansOfMeasurement + '\'' +
                '}';
    }
}

package ru.sfedu.hiber.lab4.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "OUTFIT2")
public class Outfit2 implements Serializable {
    public Outfit2() {
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "MEANSOFMEASUREMENT2"
    )
    @MapKeyColumn(name = "nameMeansOfMeasurement")
    @Column(name = "measurementError")
    protected Map<String, Double> instruments = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getInstruments() {
        return instruments;
    }

    public void setInstruments(Map<String, Double> instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outfit2 outfit2 = (Outfit2) o;
        return Objects.equals(id, outfit2.id) && Objects.equals(name, outfit2.name) && Objects.equals(instruments, outfit2.instruments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instruments);
    }

    @Override
    public String toString() {
        return "Outfit2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instruments=" + instruments +
                '}';
    }
}

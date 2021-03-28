package ru.sfedu.hiber.lab4.models;

import org.hibernate.annotations.ListIndexBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "OUTFIT1")
public class Outfit1 implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private String name;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "MEANSOFMEASUREMENT1",
            joinColumns = @JoinColumn(name = "id")
    )
    @OrderColumn
    @Column(name = "nameMeansOfMeasurement")
    //@ListIndexBase(0)
    protected List<String> instruments = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outfit1 outfit1 = (Outfit1) o;
        return Objects.equals(id, outfit1.id) && Objects.equals(name, outfit1.name) && Objects.equals(instruments, outfit1.instruments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instruments);
    }

    @Override
    public String toString() {
        return "Outfit1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instruments=" + instruments +
                '}';
    }
}

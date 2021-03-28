package ru.sfedu.hiber.lab4.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "OUTFIT")
public class Outfit implements Serializable {
    public Outfit() {
    }

    @Id
    @Column(name = "id")
    private long id;

    @Column
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "MEANSOFMEASUREMENT",
            joinColumns = @JoinColumn(name = "id_outfit")
    )
    @Column(name = "nameMeansOfMeasurement")
    protected Set<String> instruments = new HashSet<String>();

    public Long getId() {
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

    public Set<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<String> instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outfit outfit = (Outfit) o;
        return id == outfit.id && Objects.equals(name, outfit.name) && Objects.equals(instruments, outfit.instruments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instruments);
    }

    @Override
    public String toString() {
        return "Outfit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instruments=" + instruments +
                '}';
    }

}
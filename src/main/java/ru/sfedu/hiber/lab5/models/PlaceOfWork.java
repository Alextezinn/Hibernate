package ru.sfedu.hiber.lab5.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "place_of_work")
public class PlaceOfWork implements Serializable {
    public PlaceOfWork() {
    }

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceOfWork that = (PlaceOfWork) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "PlaceOfWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

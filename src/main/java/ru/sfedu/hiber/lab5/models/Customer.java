package ru.sfedu.hiber.lab5.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    public Customer() {
    }

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Outfit> outfit = new HashSet<>();

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

    public Set<Outfit> getOutfit() {
        return outfit;
    }

    public void setOutfit(Set<Outfit> outfit) {
        this.outfit = outfit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", outfit=" + outfit +
                '}';
    }
}

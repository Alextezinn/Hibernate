package ru.sfedu.hiber.lab5.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "head_of_department")
public class HeadOfDepartment implements Serializable {

    public HeadOfDepartment() {
    }

    @Id
    @Column(name = "id")
    private long id;

    @Column
    private String name;

    @Column
    private double salary;

    @OneToOne(mappedBy = "headOfDepartment")
    private Executor executor;

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeadOfDepartment that = (HeadOfDepartment) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name) && Objects.equals(executor, that.executor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, executor);
    }

    @Override
    public String toString() {
        return "HeadOfDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", executor=" + executor +
                '}';
    }
}
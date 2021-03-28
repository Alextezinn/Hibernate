package ru.sfedu.hiber.lab5.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class MyOutfit {

    public MyOutfit() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column
    private Date dateStartWork;

    @Column(nullable = true)
    private Date completionDate;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(unique = true)
    private PlaceOfWork placeWork;


    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(unique = true)
    private MeansMeasurement meansMeasurement;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(unique = true)
    private Executor executor;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "myoutfit")
//    private Set<Customer> customers = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Date dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public PlaceOfWork getPlaceWork() {
        return placeWork;
    }

    public void setPlaceWork(PlaceOfWork placeWork) {
        this.placeWork = placeWork;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public MeansMeasurement getMeansMeasurement() {
        return meansMeasurement;
    }

    public void setMeansMeasurement(MeansMeasurement meansMeasurement) {
        this.meansMeasurement = meansMeasurement;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyOutfit outfit = (MyOutfit) o;
        return id == outfit.id && Objects.equals(dateStartWork, outfit.dateStartWork) && Objects.equals(completionDate, outfit.completionDate) && Objects.equals(placeWork, outfit.placeWork) && Objects.equals(meansMeasurement, outfit.meansMeasurement) && Objects.equals(executor, outfit.executor) && Objects.equals(customer, outfit.customer) && Objects.equals(employees, outfit.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStartWork, completionDate, placeWork, meansMeasurement, executor, customer, employees);
    }

    @Override
    public String toString() {
        return "MyOutfit{" +
                "id=" + id +
                ", dateStartWork=" + dateStartWork +
                ", completionDate=" + completionDate +
                ", placeWork=" + placeWork +
                ", meansMeasurement=" + meansMeasurement +
                ", executor=" + executor +
                ", customer=" + customer +
                ", employees=" + employees +
                '}';
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MyOutfit outfit = (MyOutfit) o;
//        return id == outfit.id && Objects.equals(dateStartWork, outfit.dateStartWork) && Objects.equals(completionDate, outfit.completionDate) && Objects.equals(placeWork, outfit.placeWork) && Objects.equals(customer, outfit.customer) && Objects.equals(meansMeasurement, outfit.meansMeasurement) && Objects.equals(executor, outfit.executor) && Objects.equals(employees, outfit.employees);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, dateStartWork, completionDate, placeWork, customer, meansMeasurement, executor, employees);
//    }
//
//    @Override
//    public String toString() {
//        return "MyOutfit{" +
//                "id=" + id +
//                ", dateStartWork=" + dateStartWork +
//                ", completionDate=" + completionDate +
//                ", placeWork=" + placeWork +
//                ", customer=" + customer +
//                ", meansMeasurement=" + meansMeasurement +
//                ", executor=" + executor +
//                ", employees=" + employees +
//                '}';
//    }
}
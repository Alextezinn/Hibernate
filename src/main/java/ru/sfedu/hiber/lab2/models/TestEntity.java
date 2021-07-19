package ru.sfedu.hiber.lab2.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="test_entity")
public class TestEntity implements Serializable {

    public TestEntity(){
    }

    @Id
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date dateCreated;

    @Embedded
    private OtherEntity otherEntity;

    @Column
    private boolean checking;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OtherEntity getOtherEntity() {
        return otherEntity;
    }

    public void setOtherEntity(OtherEntity otherEntity) {
        this.otherEntity = otherEntity;
    }

    public boolean isCheck() {
        return checking;
    }

    public void setCheck(boolean checking) {
        this.checking = checking;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        TestEntity other = (TestEntity) obj;
        if(this.getId() != other.getId()){
            return false;
        }
        if(!Objects.equals(this.getName(), other.getName())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", check=" + checking +
                '}';
    }

}

package com.pvtgrupp8.monto.entities;

import com.fasterxml.jackson.annotation.*;
import org.w3c.dom.Attr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "typeofattraction")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="category",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JsonBackReference
    private List<Attraction> attractions;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public void add(Attraction tempAttraction){
        if(attractions == null){
            attractions = new ArrayList<>();
        }
        attractions.add(tempAttraction);
        tempAttraction.setCategory(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }



    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

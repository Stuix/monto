package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="city",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<District> districts;

    public City(){}

    public City(String name) {
        this.name = name;
    }

    public void add(District tempDistrict){
        if(districts == null){
            districts = new ArrayList<>();
        }
        districts.add(tempDistrict);
        tempDistrict.setCity(this);
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

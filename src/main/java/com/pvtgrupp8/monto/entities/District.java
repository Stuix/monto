package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @OneToMany(mappedBy="district",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Position> positions;


    public District(){}

    public District(String name) {
        this.name = name;
    }

    public void add(Position tempPosition){
        if(positions == null){
            positions = new ArrayList<>();
        }
        positions.add(tempPosition);
        tempPosition.setDistrict(this);
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "District{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

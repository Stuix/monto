package com.pvtgrupp8.monto.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table(name="location")
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="longitud")
    private double longitude;

    @Column(name="latitud")
    private double latitude;

    @OneToOne(mappedBy="position",
        cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties("position")
    private Attraction attraction;

    @OneToOne(mappedBy = "position")
    @JsonIgnoreProperties("position")
    private FunFact funFact;

    @ManyToOne
    @JoinColumn(name="district_id")
    @JsonIgnoreProperties("positions")
    private District district;

    public Position(){};

    public Position(double longitude, double latidude){
        this.longitude = longitude;
        this.latitude = latidude;
    }

    public Position(double longitude, double latitude, Attraction attraction, District district) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.attraction = attraction;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public FunFact getFunFact() {
        return funFact;
    }

    public void setFunFact(FunFact funFact) {
        this.funFact = funFact;
    }

    @Override
    public String toString() {
        return "Position{" +
            "id=" + id +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            ", attraction=" + attraction +
            ", district=" + district +
            '}';
    }
}

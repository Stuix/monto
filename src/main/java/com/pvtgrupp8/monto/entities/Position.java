package com.pvtgrupp8.monto.entities;

import org.w3c.dom.Attr;

import javax.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="longitude")
    private double longitude;

    @Column(name="latitude")
    private double latitude;

    @Column(name="attraction_id")
    private Attraction attraction;

    @Column(name="district_id")
    private District district;

    public Position(){};

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

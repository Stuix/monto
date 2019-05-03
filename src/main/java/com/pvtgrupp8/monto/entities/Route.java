package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="route_name")
    private String routeName;

    @OneToMany(mappedBy="route",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Rating> ratings;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User routeCreator;

    @ManyToMany(mappedBy="includedInRoutes",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Attraction> attractions;

    public Route(){};

    public Route(String routeName, List<Rating> ratings, String description, User routeCreator, List<Attraction> attractions) {
        this.routeName = routeName;
        this.ratings = ratings;
        this.description = description;
        this.routeCreator = routeCreator;
        this.attractions = attractions;
    }

    public void addRating(Rating rating){
        if(ratings==null){
            ratings = new ArrayList<>();
        }
        rating.setRoute(this);
        ratings.add(rating);
    }

    public void addAttraction(Attraction attraction){
        if(attractions==null){
            attractions = new ArrayList<>();
        }
        attraction.addToIncludedIn(this);
        attractions.add(attraction);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getRouteCreator() {
        return routeCreator;
    }

    public void setRouteCreator(User routeCreator) {
        this.routeCreator = routeCreator;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    @Override
    public String toString() {
        return "Route{" +
            "id=" + id +
            ", routeName='" + routeName + '\'' +
            ", ratings=" + ratings +
            ", description='" + description + '\'' +
            ", routeCreator=" + routeCreator +
            ", attractions=" + attractions +
            '}';
    }
}

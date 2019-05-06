package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "routeCreator",
    cascade={
        CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Route> routes;

    @OneToMany(mappedBy="ratingCreator")
    private List<Rating> ratings;

   @ManyToMany
   @JoinTable(
       name="attraction_user",
       joinColumns={@JoinColumn(name="user_id")},
       inverseJoinColumns = {@JoinColumn(name="attraction_id")}
   )
    private List<Attraction> seenAttractions;

    public User(){};

    public User(String username, String email, List<Route> routes, List<Rating> ratings, List<Attraction> seenAttractions) {
        this.username = username;
        this.email = email;
        this.routes = routes;
        this.ratings = ratings;
        this.seenAttractions = seenAttractions;
    }

    public void addCreatedRoute(Route route){
        if(routes==null){
            routes=new ArrayList<>();
        }
        route.setRouteCreator(this);
        routes.add(route);
    }

    public void addCreatedRating(Rating rating){
        if(ratings==null){
            ratings = new ArrayList<>();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Attraction> getSeenAttractions() {
        return seenAttractions;
    }

    public void setSeenAttractions(List<Attraction> seenAttractions) {
        this.seenAttractions = seenAttractions;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", routes=" + routes +
            ", ratings=" + ratings +
            ", seenAttractions=" + seenAttractions +
            '}';
    }
}

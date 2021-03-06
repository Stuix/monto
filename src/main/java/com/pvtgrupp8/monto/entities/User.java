package com.pvtgrupp8.monto.entities;

import com.fasterxml.jackson.annotation.*;
import org.json.simple.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user",uniqueConstraints = {@UniqueConstraint(columnNames= "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @NotNull(message = "Username can't be empty")
    @NotBlank(message = "Username can't be empty")
    private String username;

    @Column(name="email")
    @Email(message="Not a valid email address")
    @NotBlank(message="it must contain an email address")
    @NotNull(message="it must contain an email address")
    private String email;

    @Column(name="profile_picture")
    private String profilePicture;

    @OneToMany(mappedBy = "routeCreator",
    cascade={
        CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    //@JsonManagedReference("route-creator")
    private List<Route> routes;

    @OneToMany(mappedBy="ratingCreator",fetch = FetchType.LAZY)
    //@JsonManagedReference("rating-creator")
    private List<Rating> ratings;

   @ManyToMany
   @JoinTable(
       name="attraction_user",
       joinColumns={@JoinColumn(name="user_id")},
       inverseJoinColumns = {@JoinColumn(name="attraction_id")}
   )
    private List<Attraction> seenAttractions;

   @ManyToOne
   @JoinColumn(name="active_route_id")
   @JsonIgnoreProperties("activeUsers")
    private Route activeRoute;

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

    public Route getActiveRoute() {
        return activeRoute;
    }

    public void setActiveRoute(Route activeRoute) {
        this.activeRoute = activeRoute;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public JSONObject userInfo(){
        JSONObject user = new JSONObject();
        user.put("id",this.id);
        user.put("username",this.username);
        user.put("email",this.email);
        user.put("profilePicture",this.profilePicture);
        return user;

    }

}

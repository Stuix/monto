package com.pvtgrupp8.monto.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="rating")
    @DecimalMin("0")
    @DecimalMax("5")
    private double rating;

    @ManyToOne(
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}
    )
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User ratingCreator;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}
    )
    @JoinColumn(name="route_id")
    @JsonIgnoreProperties("ratings")
    private Route route;

    public Rating() {}

    public Rating(double rating, User ratingCreator, String comment,
        Route route) {
        this.rating = rating;
        this.ratingCreator = ratingCreator;
        this.comment = comment;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getRatingCreator() {
        return ratingCreator;
    }

    public void setRatingCreator(User ratingCreator) {
        this.ratingCreator = ratingCreator;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Rating{" +
            "id=" + id +
            ", rating=" + rating +
            ", ratingCreator=" + ratingCreator +
            ", comment='" + comment + '\'' +
            ", route=" + route +
            '}';
    }
}

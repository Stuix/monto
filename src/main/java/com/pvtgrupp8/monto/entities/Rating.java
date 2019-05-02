package com.pvtgrupp8.monto.entities;

import javax.persistence.*;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="rating")
    private double rating;

    @ManyToOne
    private User ratingCreator;
    private String comment;


    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
}

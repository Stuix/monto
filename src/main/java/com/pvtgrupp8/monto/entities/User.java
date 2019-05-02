package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "userCreator",
    cascade={
        CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Route> routes;

    @OneToMany(mappedBy="ratingCreator")
    private List<Rating> ratings;

    @ManyToMany(mappedBy="collectedByList",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Attraction> seenAttractions;


}

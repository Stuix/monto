package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
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


}

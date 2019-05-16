package com.pvtgrupp8.monto.entities;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
OneToMany: LAZY
ManyToOne: EAGER
ManyToMany: LAZY
OneToOne: EAGER
*/

@Entity
@Table(name="attractions")
public class Attraction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="title_english")
    private String titleEnglish;

    @Column(name="year_made")
    private int yearMade;

    @Column(name="description")
    private String description;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="fun_fact_id")
    @JsonIgnoreProperties("attraction")
    private FunFact funFact;

    @Column(name="picture")
    private String picture;


    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="location_id")
    @JsonIgnoreProperties("attraction") //Ignore depending on what entity gets called
    private Position position;

    @ManyToOne
    @JoinColumn(name="typeofattraction_id")
    //@JsonManagedReference
    private Category category;

    @ManyToMany
    @JoinTable(
        name="attraction_artist",
        joinColumns={@JoinColumn(name="attraction_id")},
        inverseJoinColumns ={@JoinColumn(name="artist_id")}
    )
    @JsonIgnoreProperties("attractions")
    private List<Creator> creators;

    @ManyToMany(mappedBy="attractions",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    //@JsonBackReference
    private List<Route> includedInRoutes;

    @ManyToMany(mappedBy="seenAttractions",
        cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JsonBackReference
    private List<User> collectedByList;



    public Attraction(){};

    public Attraction(String description, String picture, String title, String titleEnglish, Position position, Category category, List<Creator> creators) {
        this.description = description;
        this.picture = picture;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.position = position;
        this.category = category;
        this.creators = creators;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public void addCreator(Creator creator){
        if(creators==null) {
            creators = new ArrayList<>();
        }
        creators.add(creator);
    }

    public void addToIncludedIn(Route route){
        if(includedInRoutes == null){
            includedInRoutes=new ArrayList<>();
        }
        includedInRoutes.add(route);
    }

    public List<Route> getIncludedInRoutes() {
        List<Route> nulledList = new ArrayList<>();
        for(Route r : includedInRoutes){
            r.setAttractions(null);
            nulledList.add(r);
        }
       return nulledList;
    }



    public void setIncludedInRoutes(List<Route> includedInRoutes) {
        this.includedInRoutes = includedInRoutes;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public FunFact getFunFact() {
        return funFact;
    }

    public void setFunFact(FunFact funFact) {
        this.funFact = funFact;
    }


}

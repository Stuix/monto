package com.pvtgrupp8.monto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="attractions")
public class Attraction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="picture")
    private String picture;

    @Column(name="title")
    private String title;

    @Column(name="title_english")
    private String titleEnglish;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="location_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name="typeofattraction_id")
    private Category category;

    @ManyToMany
    @JoinTable(
        name="attraction_creator",
        joinColumns={@JoinColumn(name="attraction_id")},
        inverseJoinColumns ={@JoinColumn(name="creator_id")}
    )
    private List<Creator> creators;

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

    @Override
    public String toString() {
        return "Attraction{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", picture='" + picture + '\'' +
            ", title='" + title + '\'' +
            ", titleEnglish='" + titleEnglish + '\'' +
            ", position=" + position +
            ", category=" + category +
            ", creators=" + creators +
            '}';
    }
}

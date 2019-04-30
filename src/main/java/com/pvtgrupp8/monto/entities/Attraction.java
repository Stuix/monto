package com.pvtgrupp8.monto.entities;

import javax.persistence.*;

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
    @JoinColumn(name="position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private Creator creator;

    public Attraction(){};

    public Attraction(String description, String picture, String title, String titleEnglish, Position position, Category category, Creator creator) {
        this.description = description;
        this.picture = picture;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.position = position;
        this.category = category;
        this.creator = creator;
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

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
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
            ", creator=" + creator +
            '}';
    }
}

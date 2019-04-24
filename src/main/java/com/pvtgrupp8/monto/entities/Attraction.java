package com.pvtgrupp8.monto.entities;

import javax.persistence.*;

@Entity
@Table(name="attraction")
public class Attraction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String description;
    private String title;
    private String titleEnglish;
    private Position position;
    private Category category;
    private Creator creator;


}

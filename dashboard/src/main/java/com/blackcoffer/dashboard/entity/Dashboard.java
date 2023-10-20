package com.blackcoffer.dashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "end_year")
    private int endYear;
    @Column(name = "citylng")
    private Double cityLng;
    @Column(name = "citylat")
    private Double cityLat;
    @Column(name = "intensity")
    private int intensity;
    @Column(name = "sector")
    private String sector;
    @Column(name = "topic")
    private String topic;
    @Column(name = "insight")
    private String insight;
    @Column(name = "swot")
    private String swot;
    @Column(name = "url")
    private String url;
    @Column(name = "region")
    private String region;
    @Column(name = "start_year")
    private Integer startYear;
    @Column(name = "impact")
    private Integer impact;
    @Column(name = "added")
    private Timestamp added;
    @Column(name = "published")
    private Timestamp published;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "relevance")
    private int relevance;
    @Column(name = "pestle")
    private String pestle;
    @Column(name = "source")
    private String source;
    @Column(name = "title")
    private String title;
    @Column(name = "likelihood")
    private int likelihood;
    // Constructors, getters, setters
}
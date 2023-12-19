package com.example.entity;

import javax.persistence.*;

@Table(name = "training")
public class trainingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coach")
    private String coach;
    @Column(name = "date")
    private String date;
    @Column(name = "location")
    private String location;
    @Column(name = "time")
    private String time;
    @Column(name="activities")
    private  String activities;
    @Column(name ="players")
    private  String players;
    @Column(name="accidents")
    private String accidents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getAccidents() {
        return accidents;
    }

    public void setAccidents(String accidents) {
        this.accidents = accidents;
    }
}

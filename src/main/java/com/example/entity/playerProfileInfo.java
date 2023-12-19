package com.example.entity;

import javax.persistence.*;

@Table(name = "player_profile")
public class playerProfileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "squad")
    private String squad;
    @Column(name = "passing_level")
    private String passing_level;
    @Column(name = "tackling_level")
    private String tackling_level;
    @Column(name="kicking_level")
    private  String kicking_level;
    @Column(name ="comments")
    private  String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getPassing_level() {
        return passing_level;
    }

    public void setPassing_level(String passing_level) {
        this.passing_level = passing_level;
    }

    public String getTackling_level() {
        return tackling_level;
    }

    public void setTackling_level(String tackling_level) {
        this.tackling_level = tackling_level;
    }

    public String getKicking_level() {
        return kicking_level;
    }

    public void setKicking_level(String kicking_level) {
        this.kicking_level = kicking_level;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

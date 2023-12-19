package com.example.entity;

import javax.persistence.*;

@Table(name = "game")
public class gameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "opposition")
    private String opposition;
    @Column(name = "match_date")
    private String match_date;
    @Column(name = "location")
    private String location;
    @Column(name = "ko")
    private String ko;
    @Column(name="result")
    private  String result;
    @Column(name ="score")
    private  int score;
    @Column(name="first_comments")
    private String first_comments;
    @Column(name="opst_first_comments")
    private String opst_first_comments;
    @Column(name="second_comments")
    private String second_comments;
    @Column(name="opst_second_comments")
    private String opst_second_comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opposition) {
        this.opposition = opposition;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKO() {
        return ko;
    }

    public void setKO(String ko) {
        this.ko = ko;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFirst_comments() {
        return first_comments;
    }

    public void setFirst_comments(String first_comments) {
        this.first_comments = first_comments;
    }

    public String getOpst_first_comments() {
        return opst_first_comments;
    }

    public void setOpst_first_comments(String opst_first_comments) {
        this.opst_first_comments = opst_first_comments;
    }

    public String getSecond_comments() {
        return second_comments;
    }

    public void setSecond_comments(String second_comments) {
        this.second_comments = second_comments;
    }

    public String getOpst_second_comments() {
        return opst_second_comments;
    }

    public void setOpst_second_comments(String opst_second_comments) {
        this.opst_second_comments = opst_second_comments;
    }
}

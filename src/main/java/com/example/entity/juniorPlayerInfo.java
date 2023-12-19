package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="junior_player")
public class juniorPlayerInfo extends Account {
    @Column(name = "guardian")
    private String guardian;
    @Column(name = "coach")
    private String coach;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "health_issue")
    private String health_issue;
    @Column(name = "position")
    private String position;

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHealth_issue() {
        return health_issue;
    }

    public void setHealth_issue(String health_issue) {
        this.health_issue = health_issue;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

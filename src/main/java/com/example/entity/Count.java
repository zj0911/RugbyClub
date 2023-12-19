package com.example.entity;

public class Count {
    private int Secretary;
    private int Coach;
    private int JuniorPlayer;
    private int SeniorPlayer;

    public Count(int secretary, int coach, int juniorPlayer, int seniorPlayer) {
        this.Secretary = secretary;
        this.Coach = coach;
        this.JuniorPlayer = juniorPlayer;
        this.SeniorPlayer = seniorPlayer;
    }

    public int getSecretary() {
        return Secretary;
    }

    public void setSecretary(int secretary) {
        Secretary = secretary;
    }

    public int getCoach() {
        return Coach;
    }

    public void setCoach(int coach) {
        Coach = coach;
    }

    public int getJuniorPlayer() {
        return JuniorPlayer;
    }

    public void setJuniorPlayer(int juniorPlayer) {
        JuniorPlayer = juniorPlayer;
    }

    public int getSeniorPlayer() {
        return SeniorPlayer;
    }

    public void setSeniorPlayer(int seniorPlayer) {
        SeniorPlayer = seniorPlayer;
    }
}

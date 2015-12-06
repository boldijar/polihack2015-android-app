package com.boldijarpaul.polihack.mvp.model;


import java.io.Serializable;

public class Quest extends BaseObject implements Serializable {

    public String name;
    public double latitude;
    public double longitude;
    public Integer previousQuestId;
    public int storyId;
    public int rating;
    public Quiz quiz;
    public int color;
    public int points;
    public String hash;
    private boolean unlocked;


    public Quest() {
    }

    public Quest(int rating, Quiz quiz, double latitude, double longitude, String name, int points) {
        this.rating = rating;
        this.quiz = quiz;
        this.latitude = latitude;
        this.name = name;
        this.longitude = longitude;
        this.color = color;
        this.points = points;
    }


}

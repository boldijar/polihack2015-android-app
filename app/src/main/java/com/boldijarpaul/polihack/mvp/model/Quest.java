package com.boldijarpaul.polihack.mvp.model;


import java.io.Serializable;

public class Quest extends BaseObject implements Serializable {

    public String name;
    public double latitude;
    public double longitude;
    public Integer previousQuestId;
    public int storyId;
    public int rating;

    public int color;
    public int points;

    public String hash;

}

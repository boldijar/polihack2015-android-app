package com.boldijarpaul.polihack.mvp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Story extends BaseObject implements Serializable {

    private int userId;
    public String name;
    public int color;
    public double lat, lng;
    public int rating;

    public List<Quest> quests = new ArrayList<>();

    public int getColor() {
        return randomColor();
    }

    public Story() {
    }

    public Story(String name, int color, double lat, double lng, int rating) {
        this.name = name;
        this.color = color;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
    }

    private static Random sRandom;

    public int randomColor() {
        if (sRandom == null) {
            sRandom = new Random();
        }
        color = 0xff000000 + 256 * 256 * sRandom.nextInt(256) + 256 * sRandom.nextInt(256)
                + sRandom.nextInt(256);
        return color;
    }
}

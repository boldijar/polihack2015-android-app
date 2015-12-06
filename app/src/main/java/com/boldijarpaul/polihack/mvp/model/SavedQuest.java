package com.boldijarpaul.polihack.mvp.model;

import java.io.Serializable;

public class SavedQuest implements Serializable {
    public String name;
    public boolean available;
    public boolean finished;

    public SavedQuest() {
    }

    public SavedQuest(String name, boolean available, boolean finished) {
        this.name = name;
        this.available = available;
        this.finished = finished;
    }
}

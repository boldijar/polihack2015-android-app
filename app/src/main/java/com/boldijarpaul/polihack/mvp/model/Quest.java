package com.boldijarpaul.polihack.mvp.model;


import java.io.Serializable;

public class Quest extends BaseObject implements Serializable {

	public String name;
	public long latitude;
	public long longitude;
	public Integer previousQuestId;
	public int storyId;

	public String hash;

}

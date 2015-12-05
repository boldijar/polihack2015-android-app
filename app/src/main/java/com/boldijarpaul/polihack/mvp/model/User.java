package com.boldijarpaul.polihack.mvp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends BaseObject implements Serializable{

	 
	public String name;
	public String facebookId;
	public int score;

	public List<Quest> finishQuests = new ArrayList<Quest>();
	public List<Quest> finishStories = new ArrayList<Quest>();

}

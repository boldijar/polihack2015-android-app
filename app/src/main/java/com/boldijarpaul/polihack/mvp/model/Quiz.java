package com.boldijarpaul.polihack.mvp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends BaseObject implements Serializable {

	public String hash;
	public String question;
	public List<String> answers = new ArrayList<String>();

}

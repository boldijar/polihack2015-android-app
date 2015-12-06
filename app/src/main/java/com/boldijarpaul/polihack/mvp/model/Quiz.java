package com.boldijarpaul.polihack.mvp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz extends BaseObject implements Serializable {

    public String hash;
    public String question;
    public List<String> answers = new ArrayList<String>();

    public Quiz() {
    }

    public Quiz(String hash, String question, String... answers) {
        this.hash = hash;
        this.question = question;
        this.answers = Arrays.asList(answers);
    }
}

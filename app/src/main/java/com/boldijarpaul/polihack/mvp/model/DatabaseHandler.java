package com.boldijarpaul.polihack.mvp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DatabaseHandler {

    private List<Integer> colors = Arrays.asList(0xff1abc9c,
            0xff16a085,
            0xff2ecc71,
            0xff27ae60,
            0xff3498db,
            0xff2980b9,
            0xff9b59b6,
            0xff8e44ad,
            0xff34495e,
            0xff2c3e50,
            0xfff1c40f,
            0xfff39c12,
            0xffe67e22,
            0xffd35400,
            0xffe74c3c,
            0xffc0392b);
    private Random random = new Random();

    private int randomColor() {
        Collections.shuffle(colors);
        return colors.get(0);
    }

    private double randomLng() {
        return Math.random() / 3 + 24.6167;
    }

    private double randomLat() {
        return Math.random() / 3 + 45.6667;
    }

    private Story createStory(String name, Quest... quests) {
        Story story = new Story(name, randomColor(), randomLat(), randomLng(), random.nextInt(4));
        story.quests = Arrays.asList(quests);
        for (Quest quest : story.quests) {
            quest.color = story.color;
        }
        return story;
    }

    private Quest createQuest(String name, String hash, String question, String... answers) {
        Quiz quiz = new Quiz(hash, question, answers);
        return new Quest(random.nextInt(4), quiz, randomLat(), randomLng(), name, random.nextInt(100));
    }

    public List<Story> stories = new ArrayList<>();

    public DatabaseHandler() {
        stories.add(createStory("History is fun",
                createQuest("Near the statue", "hash1", "Who is in the statue?", "Abraham Lincoln", "Lady Gaga", "Steve Jobs"),
                createQuest("Behind Mc Donalds", "hash2", "Who is the founder of Mc Donalds?", "Ray Kroc", "Iohanis", "Traian Basescu"),
                createQuest("Near room 338", "hash3", "What was Victor Babeș?", "romanian bacteriologist", "football player", "programmer"),
                createQuest("Hint: highest building", "hash4", "Who invented the electric bulb?", "Thomas Edison", "Dani Mocanu", "Connect-R"),
                createQuest("Near the statue", "hash1", "Who is in the statue?", "Abraham Lincoln", "Lady Gaga", "Steve Jobs"),
                createQuest("Behind Mc Donalds", "hash2", "Who is the founder of Mc Donalds?", "Ray Kroc", "Iohanis", "Traian Basescu"),
                createQuest("Near room 338", "hash3", "What was Victor Babeș?", "romanian bacteriologist", "football player", "programmer"),
                createQuest("Hint: highest building", "hash4", "Who invented the electric bulb?", "Thomas Edison", "Dani Mocanu", "Connect-R"),
                createQuest("Near the statue", "hash1", "Who is in the statue?", "Abraham Lincoln", "Lady Gaga", "Steve Jobs"),
                createQuest("Behind Mc Donalds", "hash2", "Who is the founder of Mc Donalds?", "Ray Kroc", "Iohanis", "Traian Basescu"),
                createQuest("Near room 338", "hash3", "What was Victor Babeș?", "romanian bacteriologist", "football player", "programmer"),
                createQuest("Hint: highest building", "hash4", "Who invented the electric bulb?", "Thomas Edison", "Dani Mocanu", "Connect-R"),
                createQuest("Near the statue", "hash1", "Who is in the statue?", "Abraham Lincoln", "Lady Gaga", "Steve Jobs"),
                createQuest("Behind Mc Donalds", "hash2", "Who is the founder of Mc Donalds?", "Ray Kroc", "Iohanis", "Traian Basescu"),
                createQuest("Near room 338", "hash3", "What was Victor Babeș?", "romanian bacteriologist", "football player", "programmer"),
                createQuest("Hint: highest building", "hash4", "Who invented the electric bulb?", "Thomas Edison", "Dani Mocanu", "Connect-R")
        ));
        stories.add(createStory("Music is life, music is love",
                createQuest("Under the bridge", "hash5", "Who wrote the song with the quest name?", "Red Hot Chilli Peppers", "Green Day", "Dani Mocanu"),
                createQuest("Near the stairs", "hash6", "Who wrote the song 'Stairway to Heaven?", "Led Zepellin", "Goodbye To Gravity", "Vama"),
                createQuest("In front of the big trophy", "hash7", "What is the name of the leader singer of Queen?", "Freddie Mercury", "Jackson", "Bon Jovi"),
                createQuest("Under the bridge", "hash5", "Who wrote the song with the quest name?", "Red Hot Chilli Peppers", "Green Day", "Dani Mocanu"),
                createQuest("Near the stairs", "hash6", "Who wrote the song 'Stairway to Heaven?", "Led Zepellin", "Goodbye To Gravity", "Vama"),
                createQuest("In front of the big trophy", "hash7", "What is the name of the leader singer of Queen?", "Freddie Mercury", "Jackson", "Bon Jovi"),
                createQuest("Under the bridge", "hash5", "Who wrote the song with the quest name?", "Red Hot Chilli Peppers", "Green Day", "Dani Mocanu"),
                createQuest("Near the stairs", "hash6", "Who wrote the song 'Stairway to Heaven?", "Led Zepellin", "Goodbye To Gravity", "Vama"),
                createQuest("In front of the big trophy", "hash7", "What is the name of the leader singer of Queen?", "Freddie Mercury", "Jackson", "Bon Jovi"),
                createQuest("Under the bridge", "hash5", "Who wrote the song with the quest name?", "Red Hot Chilli Peppers", "Green Day", "Dani Mocanu"),
                createQuest("Near the stairs", "hash6", "Who wrote the song 'Stairway to Heaven?", "Led Zepellin", "Goodbye To Gravity", "Vama"),
                createQuest("In front of the big trophy", "hash7", "What is the name of the leader singer of Queen?", "Freddie Mercury", "Jackson", "Bon Jovi"),
                createQuest("Under the bridge", "hash5", "Who wrote the song with the quest name?", "Red Hot Chilli Peppers", "Green Day", "Dani Mocanu"),
                createQuest("Near the stairs", "hash6", "Who wrote the song 'Stairway to Heaven?", "Led Zepellin", "Goodbye To Gravity", "Vama"),
                createQuest("In front of the big trophy", "hash7", "What is the name of the leader singer of Queen?", "Freddie Mercury", "Jackson", "Bon Jovi")
        ));

        stories.add(createStory("Creepypasta"));
        stories.add(createStory("Cluj underground life"));
        stories.add(createStory("Sports 101"));
        stories.add(createStory("Mobile maniacs"));
        stories.add(createStory("IT life"));
        stories.add(createStory("Creepypasta"));
        stories.add(createStory("Cluj underground life"));
        stories.add(createStory("Sports 101"));
        stories.add(createStory("Mobile maniacs"));
        stories.add(createStory("IT life"));
        stories.add(createStory("Creepypasta"));
        stories.add(createStory("Cluj underground life"));
        stories.add(createStory("Sports 101"));
        stories.add(createStory("Mobile maniacs"));
        stories.add(createStory("IT life"));


    }
}

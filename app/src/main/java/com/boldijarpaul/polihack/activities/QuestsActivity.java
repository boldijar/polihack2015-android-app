package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.adapters.QuestAdapter;
import com.boldijarpaul.polihack.mvp.model.Quest;
import com.boldijarpaul.polihack.mvp.model.Story;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuestsActivity extends AppCompatActivity implements QuestAdapter.QuestAdapterListener {

    public static final String KEY_STORY = "keystory";
    @Bind(R.id.quests_recylcer)
    RecyclerView mRecycler;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Story mStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mStory = (Story) getIntent().getSerializableExtra(KEY_STORY);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(mStory.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecycler();
    }

    private Quest createQuest(String name, int rating, int points) {
        Quest quest = new Quest();
        quest.rating = rating;
        quest.points = points;
        quest.name = name;
        quest.latitude = 46.7667;
        quest.longitude = 23.58;
        quest.color = mStory.color;
        return quest;
    }

    private void setupRecycler() {
        List<Quest> quests = new ArrayList<>();
        quests.add(createQuest("Near the statue", 3, 10));
        quests.add(createQuest("Around some benches", 1, 1));
        quests.add(createQuest("Look back", 0, 10));
        quests.add(createQuest("Under the bridge", 2, 1));
        quests.add(createQuest("Other side", 4, 10));
        quests.add(createQuest("California map", 3, 3));
        quests.add(createQuest("Football stadium", 2, 5));
        quests.add(createQuest("Basketball stadium", 3, 30));
        quests.add(createQuest("In the guitar shop", 1, 20));
        quests.add(createQuest("Behind the bank", 3, 10));

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        QuestAdapter questAdapter = new QuestAdapter(quests, this);
        mRecycler.setAdapter(questAdapter);
        questAdapter.setListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onItemClick(Quest quest) {
        Intent intent = new Intent(this, QuestGameActivity.class);
        intent.putExtra(QuestGameActivity.KEY_QUEST, quest);
        startActivity(intent);
    }
}

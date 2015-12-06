package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.adapters.QuestAdapter;
import com.boldijarpaul.polihack.mvp.model.Quest;
import com.boldijarpaul.polihack.mvp.model.Story;

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

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        QuestAdapter questAdapter = new QuestAdapter(mStory.quests, this);
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

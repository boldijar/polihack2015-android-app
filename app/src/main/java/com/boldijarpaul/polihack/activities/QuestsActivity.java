package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.Story;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuestsActivity extends AppCompatActivity {

    public static final String KEY_STORY = "keystory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        Story story = (Story) getIntent().getSerializableExtra(KEY_STORY);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(story.name);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

package com.boldijarpaul.polihack.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.boldijarpaul.polihack.R;

public class QuizActivity extends AppCompatActivity {

    public static String KEY_HASH = "KEYH";
    private String mHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mHash = getIntent().getStringExtra(KEY_HASH);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(mHash);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

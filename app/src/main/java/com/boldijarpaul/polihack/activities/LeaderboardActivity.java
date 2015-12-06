package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.adapters.UsersAdapter;
import com.boldijarpaul.polihack.mvp.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LeaderboardActivity extends AppCompatActivity {

    @Bind(R.id.leaderboard_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupRecycler();

    }

    private User createUser(String name, int points, String facebookId) {
        User user = new User();
        user.name = name;
        user.score = points;
        user.facebookId = facebookId;
        return user;
    }

    private void setupRecycler() {
        List<User> users = new ArrayList<>();
        users.add(createUser("Boldijar Paul", 754, "100001003070997"));
        users.add(createUser("Alex Vaio", 534, "1670899814"));
        users.add(createUser("Iionel Dan", 453, "100000530606572"));
        users.add(createUser("Frig Ionut", 450, "897673960261357"));
        users.add(createUser("Alex Velea", 438, "742654302442942"));
        users.add(createUser("Billie Joe", 430, "100001451553751"));
        users.add(createUser("Adela Popescu", 415, "694005404005927"));
        users.add(createUser("Valerica Harghita", 405, "100001003070997"));
        users.add(createUser("Alex ConnectR", 401, "1670899814"));
        users.add(createUser("Vasile Dj", 399, "100000530606572"));
        users.add(createUser("Suie Paparude", 350, "897673960261357"));
        users.add(createUser("Vancea Vlad", 251, "742654302442942"));
        users.add(createUser("Vancea Alex", 212, "100001451553751"));
        users.add(createUser("Andrei Corbu", 60, "694005404005927"));


        UsersAdapter adapter = new UsersAdapter(users, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        ScaleInAnimationAdapter mScaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        mRecycler.setAdapter(mScaleInAnimationAdapter);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

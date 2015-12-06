package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.adapters.StoryAdapter;
import com.boldijarpaul.polihack.mvp.model.Story;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, StoryAdapter.StoryAdapterListener, GoogleMap.OnMarkerClickListener {

    @Bind(R.id.main_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.main_fab)
    View mFab;
    SupportMapFragment mMapFragment;
    private ArrayList<Story> stories;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;


    private Story createStory(String name, int color, double lat, double lng) {
        Story story = new Story();
        story.name = name;
        story.color = color;
        story.lat = lat;
        story.lng = lng;
        return story;
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
        if (item.getItemId() == R.id.menu_main_people) {
            startActivity(new Intent(this, LeaderboardActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.main_map_fragment);
        stories = new ArrayList<Story>();
        stories.add(createStory("History quest", 0xff0fab, 45.6667, 24.6167));
        stories.add(createStory("Narnia hunt", 0xfa0fa5, 45.637, 25.4167));
        stories.add(createStory("Colorado 23", 0x278210, 45.6167, 25.5147));
        stories.add(createStory("Spiderin", 0xab2bac, 45.6637, 25.8161));
        stories.add(createStory("Valoroa hor", 0xbacbac, 45.5617, 25.5967));
        stories.add(createStory("Music quest", 0xca127c, 45.8657, 25.6667));
        stories.add(createStory("History quest", 0xff0fab, 45.6667, 24.6167));
        stories.add(createStory("Narnia hunt", 0xfa0fa5, 45.637, 25.4167));
        stories.add(createStory("Colorado 23", 0x278210, 45.6167, 25.5147));
        stories.add(createStory("Spiderin", 0xab2bac, 45.6637, 25.8161));
        stories.add(createStory("Valoroa hor", 0xbacbac, 45.5617, 25.5967));
        stories.add(createStory("Music quest", 0xca127c, 45.8657, 25.6667));
        stories.add(createStory("History quest", 0xff0fab, 45.6667, 24.6167));
        stories.add(createStory("Narnia hunt", 0xfa0fa5, 45.637, 25.4167));
        stories.add(createStory("Colorado 23", 0x278210, 45.6167, 25.5147));
        stories.add(createStory("Spiderin", 0xab2bac, 45.6637, 25.8161));
        stories.add(createStory("Valoroa hor", 0xbacbac, 45.5617, 25.5967));
        stories.add(createStory("Music quest", 0xca127c, 45.8657, 25.6667));

        StoryAdapter adapter = new StoryAdapter(stories, this);
        adapter.setListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        mRecycler.setAdapter(mScaleInAnimationAdapter);

        mMapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        List<Marker> markers = new ArrayList<>();
        for (Story story : stories) {

            markers.add(googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(story.lat, story.lng))
                    .title(story.name)));
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cu);
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onHeaderClick() {
        mRecycler.animate().alpha(0f).start();
        mRecycler.setVisibility(View.INVISIBLE);
        mFab.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClick(Story story) {
        Intent intent = new Intent(this, QuestsActivity.class);
        intent.putExtra(QuestsActivity.KEY_STORY, story);
        startActivity(intent);
    }

    @OnClick(R.id.main_fab)
    void fabClick() {
        mRecycler.setVisibility(View.VISIBLE);
        mFab.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (Story story : stories) {
            if (story.name.equals(marker.getTitle())) {
                onItemClick(story);
                return false;
            }
        }
        return false;
    }
}

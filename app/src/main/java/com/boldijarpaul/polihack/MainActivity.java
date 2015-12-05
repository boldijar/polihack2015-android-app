package com.boldijarpaul.polihack;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.boldijarpaul.polihack.mvp.model.Story;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
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
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Bind(R.id.main_recycler)
    RecyclerView mRecycler;
    SupportMapFragment mMapFragment;
    private ArrayList<Story> stories;


    private Story createStory(String name, String color, double lat, double lng) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.main_map_fragment);
        stories = new ArrayList<Story>();
        stories.add(createStory("History quest", "ff0fab", 45.6667, 24.6167));
        stories.add(createStory("Narnia hunt", "fa0fa5", 45.637, 25.4167));
        stories.add(createStory("Colorado 23", "278210", 45.6167, 25.5147));
        stories.add(createStory("Spiderin", "ab2bac", 45.6637, 25.8161));
        stories.add(createStory("Valoroa hor", "bacbac", 45.5617, 25.5967));
        stories.add(createStory("Music quest", "ca127c", 45.8657, 25.6667));

        StoryAdapter adapter = new StoryAdapter(stories, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(adapter);

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

        LatLngBounds.Builder b = new LatLngBounds.Builder();
        for (Marker m : markers) {
            b.include(m.getPosition());
        }
        LatLngBounds bounds = b.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 2);
        googleMap.animateCamera(cu);
    }
}

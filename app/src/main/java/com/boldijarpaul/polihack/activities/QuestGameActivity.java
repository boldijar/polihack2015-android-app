package com.boldijarpaul.polihack.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.Quest;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class QuestGameActivity extends AppCompatActivity implements OnMapReadyCallback, QRCodeReaderView.OnQRCodeReadListener {

    public static String KEY_QUEST = "keyq";
    private Quest mQuest;

    private SupportMapFragment mMapFragment;
    @Bind(R.id.quest_game_fab)
    FloatingActionButton mFab;
    @Bind(R.id.quest_game_qrreader)
    QRCodeReaderView mCodeReaderView;
    @Bind(R.id.quest_game_map_holder)
    View mMapHolder;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quest_game, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_game);
        ButterKnife.bind(this);
        mQuest = (Quest) getIntent().getSerializableExtra(KEY_QUEST);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.quest_game_map);
        mMapFragment.getMapAsync(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(mQuest.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCodeReaderView.setOnQRCodeReadListener(this);

    }

    @OnClick(R.id.quest_game_fab)
    void fabClick() {
        if (mCodeReaderView.getVisibility() == View.INVISIBLE) {
            mCodeReaderView.setVisibility(View.VISIBLE);
            mMapHolder.setVisibility(View.INVISIBLE);
            mFab.setImageResource(R.drawable.ic_close_white_24dp);
            mCodeReaderView.getCameraManager().startPreview();

        } else {
            mCodeReaderView.getCameraManager().stopPreview();
            mCodeReaderView.setVisibility(View.INVISIBLE);
            mMapHolder.setVisibility(View.VISIBLE);
            mFab.setImageResource(R.drawable.ic_linked_camera_white_24dp);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(mQuest.latitude, mQuest.longitude);
        googleMap.addMarker(new MarkerOptions().position(location));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Timber.w("Actual[" + text + "], required[" + mQuest.quiz.hash + "]");

        if (mQuest.quiz.hash.equals(text) == false)
            return;

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.KEY_HASH, text);
        intent.putExtra(QuizActivity.KEY_QUIZ, mQuest.quiz);
        startActivity(intent);
        mCodeReaderView.getCameraManager().stopPreview();
        mCodeReaderView.setVisibility(View.INVISIBLE);
        mMapHolder.setVisibility(View.VISIBLE);
        mFab.setImageResource(R.drawable.ic_linked_camera_white_24dp);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.menu_quest_game_navigation) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=" + mQuest.latitude + "," + mQuest.longitude));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCodeReaderView.getVisibility() == View.VISIBLE)
            mCodeReaderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCodeReaderView.getVisibility() == View.VISIBLE)
            mCodeReaderView.getCameraManager().stopPreview();
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }
}

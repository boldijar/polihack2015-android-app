package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.Quiz;

import java.util.Arrays;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity {

    public static String KEY_HASH = "KEYH";
    private String mHash;

    @Bind(R.id.quiz_answer_1)
    TextView mAnswer1;
    @Bind(R.id.quiz_answer_2)
    TextView mAnswer2;
    @Bind(R.id.quiz_answer_3)
    TextView mAnswer3;
    @Bind(R.id.quiz_icon_1)
    ImageView mIcon1;
    @Bind(R.id.quiz_icon_2)
    ImageView mIcon2;
    @Bind(R.id.quiz_icon_3)
    ImageView mIcon3;
    @Bind(R.id.quiz_question)
    TextView mQuestion;
    @Bind(R.id.quiz_layout_1)
    View mLayout1;
    @Bind(R.id.quiz_layout_2)
    View mLayout2;
    @Bind(R.id.quiz_layout_3)
    View mLayout3;


    private Quiz mQuiz;
    private String mCorrectAnswer;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        updateCheckBoxes(1);

        mHash = getIntent().getStringExtra(KEY_HASH);
        mQuiz = new Quiz();
        mQuiz.question = "Who was the first person in space?";
        mQuiz.answers = Arrays.asList("Neil Armstrong", "Van Disel", "Kurt Cobain");
        mCorrectAnswer = mQuiz.answers.get(0);
        Collections.shuffle(mQuiz.answers);
        loadQuizViews();


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(mHash);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadQuizViews() {
        mQuestion.setText(mQuiz.question);
        mAnswer1.setText(mQuiz.answers.get(0));
        mAnswer2.setText(mQuiz.answers.get(1));
        mAnswer3.setText(mQuiz.answers.get(2));
    }

    @OnClick(R.id.quiz_layout_1)
    void clickedLayout1() {
        updateCheckBoxes(1);
    }

    @OnClick(R.id.quiz_layout_2)
    void clickedLayout2() {
        updateCheckBoxes(2);
    }

    @OnClick(R.id.quiz_layout_3)
    void clickedLayout3() {
        updateCheckBoxes(3);
    }

    private void updateCheckBoxes(int answer) {
        mIcon1.setImageResource(R.drawable.ic_close_white_24dp);
        mIcon2.setImageResource(R.drawable.ic_close_white_24dp);
        mIcon3.setImageResource(R.drawable.ic_close_white_24dp);
        if (answer == 1) {
            mIcon1.setImageResource(R.drawable.ic_check_white_24dp);
            mIcon1.animate().alpha(1f).start();
            mIcon2.animate().alpha(.5f).start();
            mIcon3.animate().alpha(.5f).start();
        }
        if (answer == 2) {
            mIcon2.setImageResource(R.drawable.ic_check_white_24dp);
            mIcon2.animate().alpha(1f).start();
            mIcon1.animate().alpha(.5f).start();
            mIcon3.animate().alpha(.5f).start();
        }
        if (answer == 3) {
            mIcon3.setImageResource(R.drawable.ic_check_white_24dp);
            mIcon3.animate().alpha(1f).start();
            mIcon1.animate().alpha(.5f).start();
            mIcon2.animate().alpha(.5f).start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

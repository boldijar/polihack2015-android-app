package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boldijarpaul.polihack.R;
import com.boldijarpaul.polihack.mvp.model.Quiz;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity {

    public static final String KEY_QUIZ = "QUIZZ";
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
    @Bind(R.id.quiz_fab)
    View mFab;


    private Quiz mQuiz;
    private int answerIndex = 1;
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
        mQuiz = (Quiz) getIntent().getSerializableExtra(KEY_QUIZ);
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
        this.answerIndex = answer;
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

    private String getOption() {
        if (answerIndex == 1) return mAnswer1.getText().toString();
        if (answerIndex == 2) return mAnswer2.getText().toString();
        if (answerIndex == 3) return mAnswer3.getText().toString();
        return "";
    }

    @OnClick(R.id.quiz_fab)
    void fabClick() {
        boolean correct = mCorrectAnswer.equals(getOption());
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle(correct ? getString(R.string.msg_congrats) : getString(R.string.msg_oops));
        builder.setMessage(correct ? getString(R.string.msg_good_answer) : getString(R.string.msg_wrong_answer));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goBackHome();
            }
        });//second parameter used for onclicklistener
        builder.show();
    }

    private void goBackHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

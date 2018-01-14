package com.ponroy.florian.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ponroy.florian.topquiz.R;
import com.ponroy.florian.topquiz.model.User;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton, mHistoric;
    private User mUser;
    //private List<User> mBest;
    protected ArrayList<User> mBest;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    public static final String PREF_KEY_SCORE_BEST_NAME_1 = "PREF_KEY_SCORE_BEST_NAME_1";
    public static final String PREF_KEY_SCORE_BEST_NAME_2 = "PREF_KEY_SCORE_BEST_NAME_2";
    public static final String PREF_KEY_SCORE_BEST_NAME_3 = "PREF_KEY_SCORE_BEST_NAME_3";
    public static final String PREF_KEY_SCORE_BEST_NAME_4 = "PREF_KEY_SCORE_BEST_NAME_4";


    public static final String PREF_KEY_SCORE_BEST_1 = "PREF_KEY_SCORE_BEST_1";
    public static final String PREF_KEY_SCORE_BEST_2 = "PREF_KEY_SCORE_BEST_2";
    public static final String PREF_KEY_SCORE_BEST_3 = "PREF_KEY_SCORE_BEST_3";
    public static final String PREF_KEY_SCORE_BEST_4 = "PREF_KEY_SCORE_BEST_4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate()");

        mUser = new User();

        mBest = new ArrayList<>();
        for(int i=0;i<4;i++)
            mBest.add(new User());

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mHistoric = (Button) findViewById(R.id.activity_main_historic_btn);

        mPlayButton.setEnabled(false);

        checkIfHistoric();

        greetUser();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mNameInput.getText().toString();
                mUser.setFirstname(firstname);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();

                // User clicked the button
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Historic.class);
                i.putParcelableArrayListExtra("HISTORIC", mBest);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            addToBestScores(mUser);
            mHistoric.setEnabled(true);
            greetUser();
        }
    }

    private void addToBestScores(User user){
        Log.w("error","error");
        if(mBest.get(0).getmBestScore() <= user.getmBestScore()){
            mBest.set(3, mBest.get(2));
            mBest.set(2, mBest.get(1));
            mBest.set(1, mBest.get(0));
            mBest.set(0, user);
            mPreferences.edit().putString(PREF_KEY_SCORE_BEST_NAME_1, user.getFirstname()).apply();
            mPreferences.edit().putInt(PREF_KEY_SCORE_BEST_1, user.getmBestScore()).apply();
            return;
        }

        Log.w("error","error");

        if(mBest.get(1).getmBestScore() <= user.getmBestScore()){
            mBest.set(3, mBest.get(2));
            mBest.set(2, mBest.get(1));
            mBest.set(1, user);
            mPreferences.edit().putString(PREF_KEY_SCORE_BEST_NAME_2, user.getFirstname()).apply();
            mPreferences.edit().putInt(PREF_KEY_SCORE_BEST_2, user.getmBestScore()).apply();
            return;
        }

        Log.w("error","error");

        if(mBest.get(1).getmBestScore() <= user.getmBestScore()){
            mBest.set(3, mBest.get(2));
            mBest.set(2, user);
            mPreferences.edit().putString(PREF_KEY_SCORE_BEST_NAME_3, user.getFirstname()).apply();
            mPreferences.edit().putInt(PREF_KEY_SCORE_BEST_3, user.getmBestScore()).apply();
            return;
        }

        Log.w("error","error");

        if(mBest.get(1).getmBestScore() < user.getmBestScore()){
            mPreferences.edit().putString(PREF_KEY_SCORE_BEST_NAME_4, user.getFirstname()).apply();
            mPreferences.edit().putInt(PREF_KEY_SCORE_BEST_4, user.getmBestScore()).apply();
            mBest.set(3, user);
        }
    }

    private void checkIfHistoric(){

        String name = mPreferences.getString(PREF_KEY_SCORE_BEST_NAME_1, "");
        int bestScore = -1;

        boolean notEmpty = false;

        if(!name.isEmpty()){
            bestScore = mPreferences.getInt(PREF_KEY_SCORE_BEST_1,-1);
            User u = new User(name, bestScore);
            mBest.set(0,u);
            notEmpty = true;
        }

        name = mPreferences.getString(PREF_KEY_SCORE_BEST_NAME_2, "");
        if(!name.isEmpty()){
            bestScore = mPreferences.getInt(PREF_KEY_SCORE_BEST_2,-1);
            User u = new User(name, bestScore);
            mBest.set(1,u);
            notEmpty = true;
        }

        if(!name.isEmpty()){
            bestScore = mPreferences.getInt(PREF_KEY_SCORE_BEST_3,-1);
            User u = new User(name, bestScore);
            mBest.set(2,u);
            notEmpty = true;
        }

        if(!name.isEmpty()){
            bestScore = mPreferences.getInt(PREF_KEY_SCORE_BEST_4,-1);
            User u = new User(name, bestScore);
            mBest.set(3,u);
            notEmpty = true;
        }

        mHistoric.setEnabled(notEmpty);
        //sorting


    }

    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Welcome back, " + firstname
                    + "!\nYour last score was " + score
                    + ", will you do better this time?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}

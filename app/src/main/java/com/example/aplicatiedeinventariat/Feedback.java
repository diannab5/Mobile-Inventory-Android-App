package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
   EditText fedNume;
   EditText fedRating;
   Button fedTrimite;

    private FeedbackDatabase  feedbackDatabase;

    private static final String SHARED_PREF_NAME = "spName";
    private static final String RATING_BAR_SHARED_KEY = "rbSharedKey";
    private RatingBar rbFeedback;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackDatabase = Room.databaseBuilder(this, FeedbackDatabase.class, "feedbackDB").allowMainThreadQueries().build();
        fedNume=(EditText)findViewById(R.id.ET_nume);
        fedRating=(EditText)findViewById(R.id.ET_feedback);
        fedTrimite=(Button)findViewById(R.id.BTN_trimite);
        fedTrimite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nume=((EditText)findViewById(R.id.ET_nume)).getText().toString();
            Float rating=((RatingBar)findViewById(R.id.RB_star)).getRating();
            FeedbackClass feedclass=new FeedbackClass(nume,rating);
            feedbackDatabase.getFeedbackDAO().insert(feedclass);
            Toast.makeText(Feedback.this, R.string.mesajtrimis,Toast.LENGTH_SHORT).show();
        }
    });
        rbFeedback =(RatingBar)findViewById(R.id.RB_star);
        //Initializare sharedpreferences
        preferences=getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);

        //Citire din fisier
        float valoare = preferences.getFloat(RATING_BAR_SHARED_KEY, -1);
        if (valoare != -1) {
            rbFeedback.setRating(valoare);
        }
        rbFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float valoare, boolean b) {
                //Salvare fisier de preferinte
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat(RATING_BAR_SHARED_KEY, valoare);
                editor.apply();
            }
        });

    }
}

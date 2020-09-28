package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class Statistics extends AppCompatActivity {

    private FeedbackDatabase feedbackDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_statistics);

        feedbackDatabase = Room.databaseBuilder(this, FeedbackDatabase.class, "feedbackDB").allowMainThreadQueries().build();
        BarChart bc=new BarChart(this,feedbackDatabase.getFeedbackDAO().getValoareDB());
        setContentView(bc);
    }
}

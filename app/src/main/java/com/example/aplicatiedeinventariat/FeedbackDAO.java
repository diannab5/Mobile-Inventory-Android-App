package com.example.aplicatiedeinventariat;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FeedbackDAO {
    @Query("SELECT rating from Feedback")
    List<Float> getValoareDB();

    @Insert
    void insert(FeedbackClass feedback);
}

package com.example.aplicatiedeinventariat;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FeedbackClass.class},version=1,exportSchema = false)
public abstract class FeedbackDatabase extends RoomDatabase{
    public abstract FeedbackDAO getFeedbackDAO();
}
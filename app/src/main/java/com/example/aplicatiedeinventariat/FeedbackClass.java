package com.example.aplicatiedeinventariat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Feedback")

public class FeedbackClass {
    @PrimaryKey(autoGenerate=true)
    @NonNull
    private int id;
    private String nume;
    private float rating;

    public FeedbackClass(@NonNull String nume, float rating) {
        this.nume = nume;
        this.rating = rating;
    }

    @NonNull
    public String getNume() {
        return nume;
    }

    public float getRating() {
        return rating;
    }

    public void setNume(@NonNull String nume) {
        this.nume = nume;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FeedbackClass{" +
                "nume='" + nume + '\'' +
                ", rating=" + rating +
                '}';
    }
}

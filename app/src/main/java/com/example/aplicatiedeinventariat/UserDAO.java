package com.example.aplicatiedeinventariat;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    //Prelucrari asupra tabelei

    @Query("SELECT * FROM User where email= :mail and parola= :password")
    User getUser(String mail, String password);

    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE nume=:nume")
    List<User> findUsersByName(String nume);

    @Query("SELECT id FROM User where nume= :nume")
    int getUserId(String nume);

    @Query("SELECT * FROM User WHERE id>1")
    List<User> getUsersById();




}

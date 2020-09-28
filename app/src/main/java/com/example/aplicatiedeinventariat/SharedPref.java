package com.example.aplicatiedeinventariat;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    SharedPreferences sharedPreferences;
    public SharedPref(Context context){
        sharedPreferences=context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setUser(int idUser){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("userId",idUser);
        editor.commit();
    }

    public int loadUser() {
        int idUser = sharedPreferences.getInt("userId", 0);
        return idUser;
    }
}

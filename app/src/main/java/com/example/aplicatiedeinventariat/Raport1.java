package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Raport1 extends AppCompatActivity {
   ListView lv;
    List<User> users = new ArrayList<>();

    private UserDAO userDAO;
    private UserDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport1);
        database= Room.databaseBuilder(this,UserDatabase.class,"userdb").allowMainThreadQueries().build();
        userDAO = database.getUserDAO();
        lv=findViewById(R.id.LV_rap1);
        users= userDAO.findUsersByName("Popescu");


        ArrayAdapter<User> adapter = new ArrayAdapter<User>(Raport1.this, android.R.layout.simple_list_item_1,users);
        lv.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.raport1ID) ;
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FileOutputStream fos=null;
                try {
                    fos=openFileOutput("fisier1.txt",MODE_PRIVATE);
                    fos.write(users.toString().getBytes());
                    Toast.makeText(Raport1.this, R.string.savedyourtext, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(fos!=null){
                        try{
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }



        });
    }

}

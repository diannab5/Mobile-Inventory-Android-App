package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Raport2 extends AppCompatActivity {

    ListView lv;
    List<User> users = new ArrayList<>();
    private UserDAO userDAO;
    private UserDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport2);

        database= Room.databaseBuilder(this,UserDatabase.class,"userdb").allowMainThreadQueries().build();
        userDAO = database.getUserDAO();

        lv=findViewById(R.id.LV_rap2);
        users= userDAO.getUsersById();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(Raport2.this, android.R.layout.simple_list_item_1,users);
        lv.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.raport2ID) ;
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FileOutputStream fos=null;
                try {
                    fos=openFileOutput("fisier2.txt",MODE_PRIVATE);
                    fos.write(users.toString().getBytes());
                    Toast.makeText(Raport2.this, R.string.SavedyourText, Toast.LENGTH_LONG).show();

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

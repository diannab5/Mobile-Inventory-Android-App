package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inregistrare extends AppCompatActivity {


    EditText textNume;
    EditText textPrenume;
    EditText textMail;
    EditText textParola;
    Button buttonInregistrare;


    private UserDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        database = Room.databaseBuilder(this, UserDatabase.class, "userdb").allowMainThreadQueries().build();


        textNume=(EditText)findViewById(R.id.ET_nume);
        textPrenume=(EditText)findViewById(R.id.ET_prenume);
        textMail=(EditText)findViewById(R.id.ET_email);
        textParola=(EditText)findViewById(R.id.ET_passwd);

        buttonInregistrare=(Button)findViewById(R.id.BTN_register);

        buttonInregistrare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (!validare()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(textNume.getText().toString(), textPrenume.getText().toString(), textMail.getText().toString(), textParola.getText().toString());
                            database.getUserDAO().insert(user);
                            startActivity(new Intent(Inregistrare.this, Login.class));

                        }
                    }, 1000);
                } else {
                    Toast.makeText(Inregistrare.this, R.string.trebuiesacompletezicampurile, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private boolean validare() {
        if (TextUtils.isEmpty(textMail.getText().toString()) ||
                TextUtils.isEmpty(textParola.getText().toString()) ||
                TextUtils.isEmpty(textNume.getText().toString()) ||
                TextUtils.isEmpty(textPrenume.getText().toString())) {
                return true;
        } else
            {
                 return false;
        }
    }

    public void renunta(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}


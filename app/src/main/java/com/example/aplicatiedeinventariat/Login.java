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

public class Login extends AppCompatActivity {
    SharedPref sp;
    private UserDAO userDAO;
    private UserDatabase database;



    EditText textUser;
    EditText textPass;
    Button buttonLogin;
    TextView tvInregistrare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUser=findViewById(R.id.ET_mailLogin);
        textPass=findViewById(R.id.ET_parolaLogin);
        buttonLogin=(Button)findViewById(R.id.BTN_login);
        tvInregistrare=(TextView)findViewById(R.id.TW_inregistrare);

        database= Room.databaseBuilder(this,UserDatabase.class,"userdb").allowMainThreadQueries().build();
        userDAO = database.getUserDAO();
        sp =new SharedPref(this);

        tvInregistrare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Login.this, Inregistrare.class);
                startActivity(it);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validare()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user=userDAO.getUser(textUser.getText().toString(),textPass.getText().toString());
                            if(user!=null){
                                sp.setUser(database.getUserDAO().getUserId(textUser.getText().toString()));
                                Intent i = new Intent(Login.this, Main2Activity.class);
                                i.putExtra("User",user);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(Login.this, R.string.youneedtoregisterfirst, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },1000);
                }else{
                    Toast.makeText(Login.this, R.string.youneedtofillallfields, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private boolean validare() {
        if (TextUtils.isEmpty(textUser.getText().toString()) || TextUtils.isEmpty(textPass.getText().toString())) {
            return true;
        }
        else
            {
            return false;
        }
    }

    }


package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1=(Button)findViewById(R.id.buttonInv);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(getApplicationContext(),Inventar.class);
                startActivity(int1);
            }
        });

        Button button2=(Button)findViewById(R.id.buttonRating);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(getApplicationContext(),Feedback.class);
                startActivity(int2);
            }
        });
        Button button3=(Button)findViewById(R.id.buttonAbout);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int3=new Intent(getApplicationContext(),About.class);
                startActivity(int3);
            }
        });
        Button button4=(Button)findViewById(R.id.buttonReparatii);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int4=new Intent(getApplicationContext(),Reparatii.class);
                startActivity(int4);
            }
        });
        Button button5=(Button)findViewById(R.id.buttonStatistici);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int5=new Intent(getApplicationContext(),Statistics.class);
                startActivity(int5);
            }
        });
        Button button6=(Button)findViewById(R.id.buttonRaport1);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int6=new Intent(getApplicationContext(),Raport1.class);
                startActivity(int6);
            }
        });
        Button button7=(Button)findViewById(R.id.buttonRaport2);
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int7=new Intent(getApplicationContext(),Raport2.class);
                startActivity(int7);
            }
        });

    }
}

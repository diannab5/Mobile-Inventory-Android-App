package com.example.aplicatiedeinventariat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Adauga extends AppCompatActivity {
Intent intent;
DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga);

        intent=getIntent();
        TextView title=(TextView)findViewById(R.id.titluID);
        title.setText(R.string.addelement);
        Spinner spinner=(Spinner)findViewById(R.id.spinnerSelect);
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.elementeCateg));
        spinner.setAdapter(spinnerAdapter);

        myRef=FirebaseDatabase.getInstance().getReference("mijloace");

          myRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  DataSnapshot ds=dataSnapshot.child("mijloace");
                  Iterable<DataSnapshot> mijloaceDS=ds.getChildren();
                  for(DataSnapshot d:mijloaceDS){
                      MijlocFix m=d.getValue(MijlocFix.class);
                      Toast.makeText(Adauga.this,m.toString(), Toast.LENGTH_LONG).show();
                  }
              }

              @Override
              public void onCancelled(DatabaseError error) {
                  Log.w( "fail","Failed to read value.", error.toException());
              }
          });

        Button okButton=findViewById(R.id.BTNIdOk);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                adaugaMijlocFix(v);
            }
        });
    }
    public void adaugaMijlocFix(View view){
        String nume=((EditText)findViewById(R.id.ETnume)).getText().toString();
        String categorie = ((Spinner)findViewById(R.id.spinnerSelect)).getSelectedItem().toString();
        String cantitateText=((EditText)findViewById(R.id.ETcantitate)).getText().toString();
        Integer cantitate=Integer.parseInt(cantitateText);
        String furnizor=((EditText)findViewById(R.id.ETfurnizor)).getText().toString();
        String pretText=((EditText)findViewById(R.id.ETpret)).getText().toString();
        Float pret=Float.parseFloat(pretText);

        DatePicker dp = findViewById(R.id.dpID);
        Calendar c = Calendar.getInstance();
        c.set(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.YYYY");
        String dataAdaugare = s.format(c.getTime());

        MijlocFix m=new MijlocFix(nume,categorie,cantitate,furnizor,pret,dataAdaugare);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mijloace");
        DatabaseReference nodMijFix=myRef.child(m.getNume());
        nodMijFix.setValue(m);

        intent.putExtra("Mij",m);
        setResult(RESULT_OK,intent);
        finish();


    }


    public void cancelAdaugare(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}

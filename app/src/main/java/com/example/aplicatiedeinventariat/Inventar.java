package com.example.aplicatiedeinventariat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Inventar extends AppCompatActivity {
  private List<MijlocFix> mijloace=new ArrayList<>();
  private int code=222;

  DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventar);

        //Populare listview cu datele preluate din firebase
        myRef= FirebaseDatabase.getInstance().getReference("mijloace");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                populare(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w( "fail",getString(R.string.failedtoreadvalue), databaseError.toException());
            }
        });
    }

    public void AdaugaMijlocFix(View v){
        if(v.getId()==R.id.BTNAdd){
            Intent it=new Intent(getApplicationContext(),Adauga.class);
            startActivityForResult(it,code);
        }

    }

    //lista cu valori de la tastatura
    private void initializareLV(){
        mijloace.add(new MijlocFix("Laptop","Aparatura birotica",10,"Altex",7500,"08.11.2019"));
        mijloace.add(new MijlocFix("Imprimanta","Aparatura birotica",3,"Cell",5000,"08.11.2019"));
        mijloace.add(new MijlocFix("Masina","Mijloace de transport",1,"Renault",73000,"08.11.2019"));
        mijloace.add(new MijlocFix("Telefon","Aparatura birotica",12,"Altex",4500,"08.11.2019"));
    }

    private void adaugaLV(){
        if(mijloace.size()==0){
            initializareLV();
        }
       // initializareLV();
        ListView lv=findViewById(R.id.LV_mij);
       // lv.invalidate();
        MijlocFixAdapter adapter=new MijlocFixAdapter(this,R.layout.item_layout,mijloace);
        lv.setAdapter(adapter);


    }

    //populare lv cu date din firebase
    private void populare(DataSnapshot ds){
       // mijloace.clear();
        for(DataSnapshot d:ds.getChildren()){
            MijlocFix mj=d.getValue(MijlocFix.class);
            mijloace.add(mj);
        }
       ListView lv=findViewById(R.id.LV_mij);
        MijlocFixAdapter adapter=new MijlocFixAdapter(this,R.layout.item_layout,mijloace);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adaugaLV();
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (this.code == requestCode) {
            if (resultCode == RESULT_OK) {
                Intent it = getIntent();
                MijlocFix mijFix = data.getParcelableExtra("Mij");
                mijloace.add(mijFix);
                adaugaLV();

            }

        }
        super.onActivityResult(requestCode,resultCode,data);
    }


}

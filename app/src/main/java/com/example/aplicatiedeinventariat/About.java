package com.example.aplicatiedeinventariat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class About extends AppCompatActivity {
   ImageView imgView;
   Button alegeButton;

   private static final int IMAGE_PICK_CODE=1000;
   private static final int PERMISSION_CODE=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imgView=findViewById(R.id.image_view);
        alegeButton=findViewById(R.id.BTN_alegeImg);

        alegeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verifica runtime permission
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                  == PackageManager.PERMISSION_DENIED){
                        //Permisiunea nu este acordata, trebuie ceruta
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //Afiseaza popup pt runtime permission
                        requestPermissions(permissions,PERMISSION_CODE);

                    }
                    else{
                        //Permisiunea a fost deja acordata
                        alegeImagineDinGalerie();
                    }
                }
                else
                {
                    alegeImagineDinGalerie();
                }

            }
        });
    }

    private void alegeImagineDinGalerie(){
        //Intent pentru a alege o imagine
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }

    //Handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     switch(requestCode){
         case PERMISSION_CODE:{
             if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                 //permsiunea a fost acordata
                 alegeImagineDinGalerie();
             }
             else{
                 //permisiunea nu a fost acordata
                 Toast.makeText(this, R.string.accesrefuzat,Toast.LENGTH_SHORT).show();
             }
         }
     }
    }

    //Handle result pt imaginea aleasa
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            //set image to image view
            imgView.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

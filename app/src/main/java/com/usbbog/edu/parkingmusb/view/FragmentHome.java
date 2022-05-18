package com.usbbog.edu.parkingmusb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentHome extends AppCompatActivity {
    ImageButton singUpbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home2);
        singUpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent i = new Intent(FragmentHome.this,registrarvehiculo.class);
                startActivity(i);
            }
        });
    }
}

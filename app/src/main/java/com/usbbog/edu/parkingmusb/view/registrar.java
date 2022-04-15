package com.usbbog.edu.parkingmusb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registrar extends AppCompatActivity {

    //VARIABLE PARA REDIRIGIR MEDIANTE EL BOTON CREADO EN EL ACTIVITY
    Button regisrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //SE ASOCIA EL BOTON POR ID AL EVENTO
        regisrar=(Button) findViewById(R.id.btnRegistrar);
        regisrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //INDICAMOS LA CLASE ORIGEN Y LA CLASE DESTINO
                Intent i = new Intent(registrar.this, home.class);
                startActivity(i);
            }
        });
    }
}
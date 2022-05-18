package com.usbbog.edu.parkingmusb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registrarvehiculo extends AppCompatActivity {
    EditText etplaca,etColor,etModelo;
    Button btnRegistrarV;

    RequestQueue requestQueue;

    private static final  String URLcrear = "http://172.17.3.72/CRUD/cv.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarvehiculo);

        requestQueue = Volley.newRequestQueue(this);

        etplaca = (EditText) findViewById(R.id.txtId);
        etColor = (EditText) findViewById(R.id.txtColor);
        etModelo = (EditText) findViewById(R.id.txtModelo);

        btnRegistrarV = (Button) findViewById(R.id.btnRegistrarV);

        btnRegistrarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });

    }

    public void CreateUser(){

        //Datos del usuario
        final String placa = etplaca.getText().toString().trim(); //trim() -> elimina los espacios al comienzo y final del String
        final String color = etColor.getText().toString().trim();
        final String modelo = etModelo.getText().toString().trim(); //trim() -> elimina los espacios al comienzo y final del String

        //Usando la implementacion Volley para conectarse a la base de datos
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,//Metodo REST que se utilizara
                URLcrear,//URL a utilizar
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(registrarvehiculo.this, "Se creo correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registrarvehiculo.this, "Error al crear", Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                }
        ){//Los datos que se enviaran a la base de datos
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", placa);
                params.put("color", color);
                params.put("modelo", modelo);
                return params;
            }
        };

        requestQueue.add(stringRequest);//Enviar la peticion
    }
}
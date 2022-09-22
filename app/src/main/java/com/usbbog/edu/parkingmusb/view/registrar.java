package com.usbbog.edu.parkingmusb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class registrar extends AppCompatActivity {
    EditText etId,etNombre,etApellido,etUser,etPass;
    Button btnCrear;

    RequestQueue requestQueue;

    private static final  String URLcrear = "http://172.17.1.122/CRUD/create.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        requestQueue = Volley.newRequestQueue(this);

        etId = (EditText) findViewById(R.id.txtId);
        etNombre = (EditText) findViewById(R.id.txtNombre);
        etApellido = (EditText) findViewById(R.id.txtApellido);
        etUser = (EditText) findViewById(R.id.txtUser);
        etPass = (EditText) findViewById(R.id.txtPass);
        btnCrear = (Button) findViewById(R.id.btnRegistrar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });

    }

    public void CreateUser(){

        //Datos del usuario
        final String idUser = etId.getText().toString().trim(); //trim() -> elimina los espacios al comienzo y final del String
        final String nombre = etNombre.getText().toString().trim();
        final String apellido = etApellido.getText().toString().trim(); //trim() -> elimina los espacios al comienzo y final del String
        final String User = etUser.getText().toString().trim();
        final String Pass=etPass.getText().toString().trim();
        final String tipoUser="2";
        //Usando la implementacion Volley para conectarse a la base de datos
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,//Metodo REST que se utilizara
                URLcrear,//URL a utilizar
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(registrar.this, "Se creo correctamente", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(registrar.this,login.class);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registrar.this, "Error al crear", Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                }
        ){//Los datos que se enviaran a la base de datos
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUser", idUser);
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("user", User);
                params.put("pass",Pass);
                params.put("tipoUser",tipoUser);
                return params;
            }
        };

        requestQueue.add(stringRequest);//Enviar la peticion
    }

}
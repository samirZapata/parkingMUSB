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

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class modificarUser extends AppCompatActivity {
    EditText etId,etNombre,etApellido,etUser,etPass,etBorrar,etUsuario,etContraseña;
    Button btnCrear,btnBuscar,btnBorrar,btnEditar;

    RequestQueue requestQueue;
    private static final  String URLEditar = "http://192.168.0.7/CRUD/editUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_user);
        requestQueue = Volley.newRequestQueue(this);

        etId = (EditText) findViewById(R.id.txtId);
        etNombre = (EditText) findViewById(R.id.txtNombre);
        etApellido = (EditText) findViewById(R.id.txtApellido);
        etUser = (EditText) findViewById(R.id.txtUser);
        etPass = (EditText) findViewById(R.id.txtPass);


        btnEditar = (Button) findViewById(R.id.btnEditar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUser();
            }
        });

    }
    public void EditUser(){
        String idUser = etId.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String user = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLEditar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(modificarUser.this, "Actualizacion lograda", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(modificarUser.this, home.class);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(modificarUser.this, "Update ERROR", Toast.LENGTH_SHORT).show();
                        System.out.println("Update ERROR: " + error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUser", idUser);
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("user", user);
                params.put("pass", pass);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
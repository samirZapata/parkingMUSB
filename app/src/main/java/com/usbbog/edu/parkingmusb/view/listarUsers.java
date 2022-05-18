package com.usbbog.edu.parkingmusb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class listarUsers extends AppCompatActivity {
    EditText etBorrar;
    Button btnEliminar;

    final String IP="172.17.3.72";
    final String sitio="CRUD";
    RequestQueue requestQueue;
    ListView lista;
    String nombre, apellido;
    String[] elementos;
    private static final  String URLBorrar = "http://172.17.3.72/CRUD/borrarUser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilante);
        requestQueue = Volley.newRequestQueue(this);
        lista=(ListView) findViewById(R.id.lvLista);
        ejecutarURL();
        requestQueue = Volley.newRequestQueue(this);

        etBorrar = (EditText) findViewById(R.id.txtUser);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteUser();
            }
        });
    }
    public void ejecutarURL(){
        listaUsuario("http://"+IP+"/"+sitio+"/"+"listarUsers.php");
    }
    public void mostrar() {
        ArrayAdapter<String> adapter = new ArrayAdapter(listarUsers.this,
                android.R.layout.simple_list_item_1, elementos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {//Al hacer click en cualquiera de los elementos de la lista
            }
        });
    }

    public void listaUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
            JSONObject jsonObject;
            elementos = new String[response.length()];
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    nombre = jsonObject.getString("nombre");
                    apellido = jsonObject.getString("apellido");
                    elementos[i] = nombre+" "+ apellido;
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            mostrar();
        }, error -> Toast.makeText(getApplicationContext(), "ERROR DE CONEXION",
                Toast.LENGTH_SHORT).show()
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void DeleteUser(){
        String idUser = etBorrar.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLBorrar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(listarUsers.this, "Se borro correctamente", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(listarUsers.this,listarUsers.class);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(listarUsers.this, "Error al borrar", Toast.LENGTH_SHORT).show();
                        System.out.println("DELETE ERROR: " + error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUser", idUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}

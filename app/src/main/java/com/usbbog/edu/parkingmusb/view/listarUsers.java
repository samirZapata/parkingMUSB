package com.usbbog.edu.parkingmusb.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class listarUsers extends AppCompatActivity {

    final String IP="192.168.0.4";
    final String sitio="CRUD";
    RequestQueue requestQueue;
    ListView lista;
    String doc, nombre, apellido, usuario, clave, perfil;
    String[] elementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilante);
        requestQueue = Volley.newRequestQueue(this);
        lista=(ListView) findViewById(R.id.lvLista);
        ejecutarURL();
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
                    doc = jsonObject.getString("idUser");
                    nombre = jsonObject.getString("nombre");
                    apellido = jsonObject.getString("apellido");
                    usuario = jsonObject.getString("user");
                    clave = jsonObject.getString("pass");
                    perfil = jsonObject.getString("tipoUser");
                    elementos[i] = doc+" "+nombre+" "+ apellido+" "+usuario+"  "+clave+"  "+perfil + " ";
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


}

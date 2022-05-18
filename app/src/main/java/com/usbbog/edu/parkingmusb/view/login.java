package com.usbbog.edu.parkingmusb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.usbbog.edu.parkingmusb.view.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

public class login extends AppCompatActivity {

    public static final String nombre ="nombre";
    static EditText txtUser, txtPass;
    Button bLogin;
    TextView btnIrRegistrar;
    String per_usuario1 ="1";
    String per_usuario2 ="2";
    final static String IP = "192.168.0.7";
    final static String IPUSB = "172.17.3.72";
    final static String sitio = "CRUD";
    RequestQueue requestQueue;

    Button singUpbtn;

    //TextInputLayout user = findViewById(R.id.txtUser);
    //TextInputLayout pass = findViewById(R.id.txtPass);
    //EditText inputUno;
    //EditText inputDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);

        //inputUno = user.getEditText();
        //String inputTextUno = inputUno.getText().toString().trim();

        //-------------------------------------------------------------------------

        //inputDos = pass.getEditText();
        //String inputTextDos = inputDos.getText().toString().trim();


        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);

        bLogin = (Button) findViewById(R.id.btnIniciarSesion);
        btnIrRegistrar = (TextView) findViewById(R.id.btnIrRegistrar);
        btnIrRegistrar.setOnClickListener(v ->
                registrar());
        bLogin.setOnClickListener(v ->
                loginUsuario("http://"+IPUSB+"/"+sitio+"/"+"login.php?user="+txtUser.getText().toString()+
                        "&pass="+txtPass.getText().toString()));

                /*loginUsuario("http://"+IPUSB+"/"+sitio+"/"+"login.php?user="+txtUser.getText().toString()+
                "&pass="+txtPass.getText().toString()));*/


    }
    public void registrar(){
       Intent i=new Intent(login.this,registrar.class);
       startActivity(i);
    }

    public void loginUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
            JSONObject jsonObject;
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = (JSONObject) response.getJSONObject(i);
                    if(txtUser.getText().toString().equals(jsonObject.getString("user")) &&
                            txtPass.getText().toString().equals(jsonObject.getString("pass"))){
                        if (per_usuario1.equals(jsonObject.getString("tipoUser"))) {
                            Intent j = new Intent(login.this, listarUsers.class);
                            j.putExtra("nombre", jsonObject.getString("nombre"));
                            startActivity(j);
                        }

                        else if(per_usuario2.equals(jsonObject.getString("tipoUser"))){
                            Intent j = new Intent(login.this, home.class);
                            j.putExtra("nombre", jsonObject.getString("nombre"));
                            startActivity(j);
                        }


                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> Toast.makeText(getApplicationContext(), "ERROR DE CONEXION",
                Toast.LENGTH_SHORT).show()
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}
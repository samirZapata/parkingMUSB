package com.usbbog.edu.parkingmusb.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.security.AccessControlContext;
import java.security.AccessController;

public class HomeFragment<fragment_home2> extends Fragment {
    ImageButton singUpbtn;

    Button registrar; //BOTON PARA IR A REGISTRAR VEHICULO

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home2, container, false);
        singUpbtn = v.findViewById(R.id.btnNews);

        final AccessControlContext context;
        //context = registrarvehiculo.getContext();


        singUpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

            }
        });
        return v;

    }
            /*  FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //agrega el Fragment en el contenedor, en este caso el FrameLayout con id `FrameLayout`.
                ft.add(R.id.g, new ClothingsSetsFragment());
                ft.commit();
                Intent i = new Intent(getActivity(), newsFragment.class);
                startActivity(i);*/

}
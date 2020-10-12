package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton camaraBtn;
    private ImageButton contactosBtn;
    private ImageButton locationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camaraBtn = findViewById(R.id.permisosCamara);
        contactosBtn = findViewById(R.id.contactosPermisos);
        locationBtn =  findViewById(R.id.locationBtn);
        //Contactos Permisos
        contactosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityContacts.class);
                startActivity(intent);
            }
        });
        // Cámara Permisos
        camaraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCamara.class);
                startActivity(intent);
            }
        });
    }
}

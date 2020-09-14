package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class infoPais extends AppCompatActivity {
    private TextView nombreP, nombrePi, sigla, capital;
    private ImageView bandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String siglaI;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pais);
        nombreP = findViewById(R.id.nombrePaisInfoPais);
        nombrePi = findViewById(R.id.nombreIPais);
        sigla = findViewById(R.id.siglaInfoPais);
        capital = findViewById(R.id.capitalInfoPais);
        bandera = findViewById(R.id.banderaInfoPais);
        Intent intent = getIntent();
        siglaI = intent.getStringExtra("sigla");
        nombreP.setText(intent.getStringExtra("nombre"));
        nombrePi.setText(intent.getStringExtra("nombreI"));
        sigla.setText(siglaI);
        capital.setText(intent.getStringExtra("capital"));
        String url = "https://www.countryflags.io/"+siglaI+"/shiny/64.png";
        Picasso.with(this).load(url).into(bandera);
    }
}
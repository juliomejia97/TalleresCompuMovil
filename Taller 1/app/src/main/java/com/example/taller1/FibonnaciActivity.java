package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.List;

public class FibonnaciActivity extends AppCompatActivity {
    private String[] sequence;
    private ListView listSequence;
    private ImageButton creador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonnaci);
        Intent intent = getIntent();
        //Llenar la secuencia fibonacci
        Integer number = intent.getIntExtra("number",0);
        generarSecuencia(number);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sequence);
        listSequence = findViewById(R.id.lista);
        listSequence.setAdapter(adapter);
        //Inflar el boton de imagen
        creador = findViewById(R.id.creador);
        creador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci");
                Intent wikiFibonacci = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(wikiFibonacci);
            }
        });
    }

    private void generarSecuencia(int n){
        sequence = new String[n];
        for(int i=0; i < n; i++){
            sequence[i] = getNumberFibo(i);
        }
    }

    private String getNumberFibo(int n){
        double A = (1 + Math.sqrt(5))/2;
        double B = (1 - Math.sqrt(5))/2;
        double fib = (Math.pow(A,n) - Math.pow(B,n))/ Math.sqrt(5);
        return String.valueOf(Math.floor(fib));
    }
}
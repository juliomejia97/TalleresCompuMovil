package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Factorial extends AppCompatActivity {

    private TextView resultado, operaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);
        //Recibir datos
        Intent intent = getIntent();
        Integer n = intent.getIntExtra("number", 1);
        resultado = findViewById(R.id.resultado);
        operaciones = findViewById(R.id.operación);
        factorial(n);
    }

    private void factorial(int n){
        int reslt = 1;
        String textOperaciones = "";
        for (int i = 1; i<=n;i++){
            textOperaciones += Integer.toString(i);
            if(i!=n){
                textOperaciones += '*';
            }
            reslt = reslt * i;
        }
        operaciones.setText(textOperaciones);
        resultado.setText(Integer.toString(reslt));
    }
}
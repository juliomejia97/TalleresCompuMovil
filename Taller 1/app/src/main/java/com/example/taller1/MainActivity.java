package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> options;
    private Spinner spinnerOptions;
    private ArrayAdapter<String> adapter;
    private Button fiboButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializar els spinner
        initOptions();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions = findViewById(R.id.options);
        spinnerOptions.setAdapter(adapter);
        //Inflar boton de Fibo
        fiboButton = findViewById(R.id.fibo);
        fiboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText number = findViewById(R.id.posiciones);
                //Si está lleno el campo hacer intent, sino mostar toast
                if(number.getText().length()==0) {
                    Toast.makeText(view.getContext(), "¡No se ha ingresado un número!", Toast.LENGTH_SHORT).show();
                }else{
                    Integer posiciones = Integer.parseInt(String.valueOf(number.getText()));
                    Intent intent = new Intent(view.getContext(),FibonnaciActivity.class);
                    intent.putExtra("number",posiciones);
                    startActivity(intent);
                }
            }
        });

    }

    void initOptions(){
        options = new ArrayList<String>();
        for(int i=0; i <= 15; i++)
            options.add(String.valueOf(i));
    }
}
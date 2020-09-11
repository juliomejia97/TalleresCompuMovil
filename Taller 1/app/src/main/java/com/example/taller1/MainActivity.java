package com.example.taller1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> options;
    private Spinner spinnerOptions;
    private ArrayAdapter<String> adapter;
    private Button fiboButton, factorialButton;
    private static int cantFibo = 0;
    private static int cantFacto = 0;
    private LocalDateTime fechaFibo, fechaFacto;
    private TextView horaFibo, horaFacto, numeroFibo, numeroFacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inflar componentes
        fiboButton = findViewById(R.id.fibo);
        factorialButton = findViewById(R.id.factorial);
        //Inicializar el spinner
        initOptions();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions = findViewById(R.id.options);
        spinnerOptions.setAdapter(adapter);
        //Listener de fibo
        fiboButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                    cantFibo++;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        fechaFibo = LocalDateTime.now();
                    }
                    startActivity(intent);
                }
            }
        });
        //Listener de factorial
        factorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String option = spinnerOptions.getSelectedItem().toString();
                if(option.isEmpty() ){
                    Toast.makeText(view.getContext(), "¡No se ha escogido un número!", Toast.LENGTH_SHORT).show();
                }else{
                    Integer n = Integer.parseInt(option);
                    Intent intent = new Intent(view.getContext(), Factorial.class);
                    intent.putExtra("number",n);
                    cantFacto++;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        fechaFacto = LocalDateTime.now();
                    }
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fechaFacto != null){
            horaFacto = findViewById(R.id.horaFactorial);
            numeroFacto = findViewById(R.id.cantidadFactorial);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String format = fechaFacto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm"));
                horaFacto.setText(format);
            }
            numeroFacto.setText(Integer.toString(cantFacto));
        }
        if(fechaFibo != null){
            horaFibo = findViewById(R.id.horaFibo);
            numeroFibo = findViewById(R.id.cantidadFibo);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String format = fechaFibo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm"));
                horaFibo.setText(format);
            }
            numeroFibo.setText(Integer.toString(cantFibo));
        }
    }

    void initOptions(){
        options = new ArrayList<String>();
        for(int i=1; i <= 15; i++)
            options.add(String.valueOf(i));
    }
}
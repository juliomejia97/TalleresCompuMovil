package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Paises extends AppCompatActivity {
    private ArrayAdapter<Pais> adapter;
    private ArrayList<Pais> paises;
    private ListView listaPaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises);
        cargarPaises();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paises);
        listaPaises = findViewById(R.id.listaPaises);
        listaPaises.setAdapter(adapter);

        listaPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pais pais = (Pais) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), infoPais.class);
                intent.putExtra("nombre", pais.getNombre());
                intent.putExtra("nombreI", pais.getNombreI());
                intent.putExtra("sigla", pais.getSigla());
                intent.putExtra("capital", pais.getCapital());
                startActivity(intent);
            }
        });
    }

    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = this.getAssets().open("paises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private void cargarPaises(){
        paises = new ArrayList<Pais>();
        JSONObject json= null;
        JSONArray paisesJsonArray = null;
        try {
            json = new JSONObject(loadJSONFromAsset());
            paisesJsonArray = json.getJSONArray("paises");
            for(int i=0;i < paisesJsonArray.length(); i++){
                JSONObject jsonObject =  paisesJsonArray.getJSONObject(i);
                String capital = jsonObject.getString("capital");
                String nombre = jsonObject.getString("nombre_pais");
                String nombreI = jsonObject.getString("nombre_pais_int");
                String sigla = jsonObject.getString("sigla");
                Pais actual = new Pais(capital,nombre,nombreI,sigla);
                paises.add(actual);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
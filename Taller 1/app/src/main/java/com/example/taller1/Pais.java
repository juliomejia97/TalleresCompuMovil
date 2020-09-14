package com.example.taller1;

public class Pais {
    private String capital;
    private String nombre;
    private String nombreI;
    private String sigla;

    public Pais(String capital, String nombre, String nombreI, String sigla) {
        this.capital = capital;
        this.nombre = nombre;
        this.nombreI = nombreI;
        this.sigla = sigla;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreI() {
        return nombreI;
    }

    public void setNombreI(String nombreI) {
        this.nombreI = nombreI;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

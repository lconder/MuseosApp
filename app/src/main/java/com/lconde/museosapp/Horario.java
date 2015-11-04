package com.lconde.museosapp;

public class Horario
{
    String día;
    String hora;

    public Horario(String día, String hora) {
        this.día = día;
        this.hora = hora;
    }

    public String getDía() {
        return día;
    }

    public String getHora() {
        return hora;
    }

    public void setDía(String día) {
        this.día = día;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

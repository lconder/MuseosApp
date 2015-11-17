package com.lconde.museosapp;

public class Horario
{
    String dia;
    String horaApertura;
    String horaCierre;


    public Horario(String dia, String horaApertura, String horaCierre)
    {
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Horario()
    {
        this.dia="";
        this.horaCierre="";
        this.horaApertura="";
    }

    public String getDía() {
        return dia;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setDía(String día) {
        this.dia = dia;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public boolean  buscarDia(String dia)
    {

        if (this.dia == dia)
            return true;
        else
            return false;
    }
}

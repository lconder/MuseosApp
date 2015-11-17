package com.lconde.museosapp;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Museo
{
    String nombre;
    int id;
    String imagen;
    String telefono;
    String direccion;
    String latitud;
    String longitud;
    String descripcionCorta;
    String descripcionLarga;
    String facebook;
    String facebookId;
    String twitter;
    String instagram;
    String web;
    List<Horario> horarios;


    public Museo(String nombre, int id, String imagen, String telefono, String direccion, String latitud, String longitud, String descripcionCorta, String descripcionLarga, String facebook,String facebookId, String twitter, String instagram, String web,List<Horario> horarios)
    {
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.facebook = facebook;
        this.facebookId = facebookId;

        this.twitter = twitter;
        this.instagram = instagram;
        this.web = web;
        this.horarios = horarios;
    }

    public Museo(String nombre, int id, String imagen,String latitud,String longitud)
    {
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Museo(String nombre, int id, String imagen)
    {
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getWeb() {
        return web;
    }


    public void setFacebookId(String facebookId) {this.facebookId = facebookId;}

    public String getFacebookId() {return facebookId;}

    public boolean isOpen()
    {
        boolean bandera = true;
        String horaApertura = "";
        String horaCierre = "";


        Calendar now = Calendar.getInstance();
        //System.out.println("Día de la semana:" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+"Hora del dia: "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE));
        String dia = now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        String horaActual = now.get(Calendar.HOUR_OF_DAY)+"";
        String minutosActuales = now.get(Calendar.MINUTE)+"";

        switch (dia)
        {
            case "lunes":
                    Horario lunes = this.buscaDia("Lunes");
                    if(lunes!=null)
                    {
                        horaApertura = lunes.getHoraApertura();
                        horaCierre = lunes.getHoraCierre();
                        bandera=comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);

                    }
                    break;
            case "martes":
                    System.out.println("Hoy es Martes");
                    break;
            case "miércoles":
                    Horario miercoles = this.buscaDia("Miércoles");
                    if(miercoles!=null)
                    {
                        horaApertura = miercoles.getHoraApertura();
                        horaCierre = miercoles.getHoraCierre();
                        bandera= comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);

                    }
                    break;
            case "jueves":
                    Horario jueves = this.buscaDia("Jueves");
                    if(jueves!=null)
                    {
                        horaApertura = jueves.getHoraApertura();
                        horaCierre = jueves.getHoraCierre();
                        bandera = comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);
                    }
                    break;
            case "viernes":
                    System.out.println("Hoy es Viernes");
                    break;
            case "sábado":
                    System.out.println("Hoy es Sábado");
                    break;
            case "domingo":
                    System.out.println("Hoy es Domingo");
                    break;
        }

        return bandera;
    }

    public Horario buscaDia(String dia)
    {
        Horario temporal = new Horario();
        for(Horario i:this.horarios)
        {
            if(dia.equals(i.getDía()))
            {
                temporal.setDía(i.getDía());
                temporal.setHoraApertura(i.getHoraApertura());
                temporal.setHoraCierre(i.getHoraCierre());
            }
        }
        return temporal;
    }

    public boolean comparaHora(String horaActual, String minutosActuales, String Apertura, String Cierre)
    {
        Boolean bandera = false;
        String horaApertura="";
        String horaCierre="";
        String minutosApertura="";
        String minutosCierre="";
        //Si hora Actual esta antes de hora Apertura && hora Actual esta despues de hora Cierre
        for(int i=0; i<Apertura.length();i++)
        {
            if(Apertura.charAt(i) != ':')
                horaApertura += Apertura.charAt(i);
            else
                break;
        }

        for(int i=0; i<Cierre.length();i++)
        {
            if(Cierre.charAt(i) != ':')
                horaCierre += Cierre.charAt(i);
            else
                break;
        }
       // System.out.println("Hora Apertura: "+horaApertura+" < "+horaActual +" >Hora de Cierre: "+horaCierre);

        if(Integer.parseInt(horaApertura) == 0 || Integer.parseInt(horaCierre) == 0)
        {
            bandera = false;

        }
        else
        {

            if (compareTo(Integer.parseInt(horaApertura), Integer.parseInt(horaActual)) == 1) {
                bandera = true;
                System.out.println("Abierto1 porque: " + horaApertura + " <= " + horaActual);

                if (compareTo(Integer.parseInt(horaActual), Integer.parseInt(horaCierre)) == 1) {
                    bandera = true;
                    System.out.println("Abierto2 porque: " + horaActual + " <= " + horaCierre);
                } else {
                    bandera = false;
                    System.out.println("!Cerrado2: " + horaActual + "<= " + horaCierre);
                }
            } else {
                bandera = false;
                System.out.println("!Cerrado 1:  " + horaApertura + " <= " + horaActual);

            }
        }
        return bandera;
    }

    public int compareTo(int hora1, int hora2)
    {
        System.out.println("Hora 1:"+hora1+" Hora 2: "+hora2);
        int retorno=0;
        if(hora1 > hora2)
        {  retorno= -1;

        }
        else if(hora1 < hora2)
        {  retorno= 1;}
        else if(hora1 == hora2)
        {   retorno= 1;}

        return retorno;
    }


}

package com.lconde.museosapp.Classes;


import com.lconde.museosapp.Classes.Horario;

import java.util.Calendar;
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
    String facebook;
    String facebookId;
    String twitter;
    String twitterId;
    String instagram;
    String web;
    List<Horario> horarios;


    public Museo(String nombre, int id, String imagen, String telefono, String direccion, String latitud, String longitud, String descripcionCorta, String facebook,String facebookId, String twitter, String twitterId, String instagram, String web,List<Horario> horarios)
    {
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionCorta = descripcionCorta;
        this.facebook = facebook;
        this.facebookId = facebookId;

        this.twitter = twitter;
        this.twitterId = twitterId;
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

    public String getTwitterId() {return twitterId;}

    public void setTwitterId(String twitterId) {this.twitterId = twitterId;}

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
                    Horario martes = this.buscaDia("Martes");
                    if(martes != null)
                    {
                        horaApertura = martes.getHoraApertura();
                        horaCierre = martes.getHoraCierre();
                        bandera= comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);

                    }
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
                    Horario viernes = this.buscaDia("Viernes");
                    if(viernes!=null)
                    {
                        horaApertura = viernes.getHoraApertura();
                        horaCierre = viernes.getHoraCierre();
                        bandera = comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);
                    }
                    break;
            case "sábado":
                    Horario sabado = this.buscaDia("Sábado");
                    if(sabado!=null)
                    {
                        horaApertura = sabado.getHoraApertura();
                        horaCierre = sabado.getHoraCierre();
                        bandera = comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);
                    }
                    break;
            case "domingo":
                    Horario domingo = this.buscaDia("Domingo");
                    if(domingo!=null)
                    {
                        horaApertura = domingo.getHoraApertura();
                        horaCierre = domingo.getHoraCierre();
                        bandera = comparaHora(horaActual,minutosActuales,horaApertura,horaCierre);
                    }
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

        if(Integer.parseInt(horaApertura) == 0 || Integer.parseInt(horaCierre) == 0)
        {
            bandera = false;

        }
        else
        {

            if (compareTo(Integer.parseInt(horaApertura), Integer.parseInt(horaActual)) == 1) {
                bandera = true;
                if (compareTo(Integer.parseInt(horaActual), Integer.parseInt(horaCierre)) == 1) {
                    bandera = true;
                } else {
                    bandera = false;
                }
            } else {
                bandera = false;
            }
        }
        return bandera;
    }

    public int compareTo(int hora1, int hora2)
    {
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

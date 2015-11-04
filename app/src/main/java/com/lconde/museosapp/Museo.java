package com.lconde.museosapp;


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
    String twitter;
    String instagram;
    String web;
    List<Horario> horarios;

    public Museo(String nombre, int id, String imagen, String telefono, String direccion, String latitud, String longitud, String descripcionCorta, String descripcionLarga, String facebook, String twitter, String instagram, String web,List<Horario> horarios)
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

    public boolean isOpen()
    {
        Calendar now = Calendar.getInstance();
        Date noww = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("K:mm a");
        String formattedTime = sdf.format(noww);
        System.out.println("Día de la semana:" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+"Hora del dia: "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE));
        return true;
    }
}

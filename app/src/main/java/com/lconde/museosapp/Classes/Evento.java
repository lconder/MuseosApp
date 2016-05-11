package com.lconde.museosapp.Classes;


public class Evento
{

    private Integer id;
    private String titulo;
    private String lugar;
    private String descripcion;
    private String link;
    private String imagen;
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Evento(Integer id, String titulo, String lugar, String descripcion, String link, String imagen, String fecha) {

        this.id = id;
        this.titulo = titulo;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.link = link;
        this.imagen = imagen;
        this.fecha = fecha;
    }

    public Evento()
    {}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }

    public String getImagen() {
        return imagen;
    }
}

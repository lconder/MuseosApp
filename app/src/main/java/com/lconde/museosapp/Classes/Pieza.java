package com.lconde.museosapp.Classes;

public class Pieza
{
    private String nombre;
    private String categoria;
    private  int id;
    private  String autor;
    private String anio;
    private String descripcion;
    private String imagen;

    public Pieza(String nombre, String categoria, int id, String autor, String anio, String descripcion, String imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.id = id;
        this.autor = autor;
        this.anio = anio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getAnio() {
        return anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean searchId(int Id)
    {
        if(this.id == Id)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Nueva Pieza--> Id: "+this.id+ " Nombre: "+ this.nombre +"Imagen:"+this.imagen+"\n";
    }
}

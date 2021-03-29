package com.teambaa.notiapp.vistaCards;

public class Nota
{
    String id;
    String titulo;
    String nota;
    String prioridad;

    public Nota()
    {
    }

    public Nota(String id, String titulo, String nota, String prioridad)
    {
        this.id = id;
        this.titulo = titulo;
        this.nota = nota;
        this.prioridad = prioridad;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getNota()
    {
        return nota;
    }

    public void setNota(String nota)
    {
        this.nota = nota;
    }

    public String getPrioridad()
    {
        return prioridad;
    }

    public void setPrioridad(String prioridad)
    {
        this.prioridad = prioridad;
    }
}

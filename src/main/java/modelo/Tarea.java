package modelo;

import java.io.Serializable;

public class Tarea implements Serializable {
    String titulo;
    String descripcion;
    String prioridad;
    boolean completada;
    int cod_tarea;

    public Tarea(String titulo, String descripcion, String prioridad,boolean completada, int cod_tarea){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = completada;
        this.cod_tarea = cod_tarea;
    }

    //Getters Tarea
    public String getTitulo(){
        return titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public boolean isCompletada(){
        return completada;
    }

    public String getPrioridad(){
        return prioridad;
    }

    public int getCodigo(){
        return cod_tarea;
    }

    //Setters Tarea
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setCompletada(boolean completada){
        this.completada = completada;
    }


    public void setPrioridad(String prioridad){
        this.prioridad=prioridad;
    }


}

package model;

import java.util.Date;

public class Libro {
    private long    id;
    private String  titulo;
    private String  nombreautor;
    private String  genero;
    private String  idioma;
    private String  fechapublicacion;


    public Libro(){
        super();
    }


    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }


    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }


    public String getNombreautor(){
        return nombreautor;
    }
    public void setNombreautor(String nombreautor){
        this.nombreautor = nombreautor;
    }


    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }


    public String getIdioma(){
        return idioma;
    }
    public void setIdioma(String idioma){
        this.idioma = idioma;
    }


    public String getFechapublicacion(){
        return fechapublicacion;
    }
    public void setFechapublicacion(String fechapublicacion){
        this.fechapublicacion = fechapublicacion;
    }
    //------------------------------------------------------------------------------------------------------------------
}

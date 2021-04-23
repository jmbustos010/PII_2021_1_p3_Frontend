package model;

public class Tutorias {

    private long id;
    private String nombretutor;
    private String materia;
    private String horario;
    private String ubicacion;
    private long   precio;


    public Tutorias(){

    }

    public Tutorias(long id, String nombretutor, String materia, String horario, String ubicacion, long precio){
        this.id  = id;
        this.nombretutor = nombretutor;
        this.materia = materia;
        this.horario = horario;
        this.ubicacion = ubicacion;
        this.precio = precio;
    }

    /////setters///


    public void setid(long id) {
        this.id = id;
    }

    public void setnombretutor(String nombretutor) {
        this.nombretutor = nombretutor;
    }

    public void setmateria(String materia) {
        this.materia = materia;
    }

    public void sethorario(String horario) {
        this.horario = horario;
    }

    public void setubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setprecio(long precio) {
        this.precio = precio;
    }

    //////getters/////

    public long getprecio() {
        return precio;
    }

    public long getid() {
        return id;
    }

    public String getubicacion() {
        return ubicacion;
    }

    public String gethorario() {
        return horario;
    }

    public String getmateria() {
        return materia;
    }

    public String getnombretutor() {
        return nombretutor;
    }
}

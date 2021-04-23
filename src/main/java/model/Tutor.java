package model;

public class Tutor {private  long id;
    private  String nombre;
    private  String fechanacimiento;
    private  long   numeroidentidad;
    private  long   telefono;
    private  String claseimpartida;

    public Tutor(){
    }

    public Tutor(long id, String nombre, String fechanacimiento, long numeroidentidad, long telefono, String claseimpartida ){
        this.id = id;
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.numeroidentidad = numeroidentidad;
        this.telefono = telefono;
        this.claseimpartida = claseimpartida;
    }

    /////setters////

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public void setNumeroidentidad(long numeroidentidad) {
        this.numeroidentidad = numeroidentidad;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setClaseimpartida(String claseimpartida) {
        this.claseimpartida = claseimpartida;
    }

    ////getters///


    public long getId() {
        return id;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public long getNumeroidentidad() {
        return numeroidentidad;
    }

    public String getclaseimpartida() {
        return claseimpartida;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }
}

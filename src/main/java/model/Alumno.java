package model;


public class Alumno {
    private long    id;
    private String  nombre;
    private long    telefono;
    private long    numeroidentidad;
    private String  fechanacimiento;
    private String  carrera;


    public Alumno(){
        super();
    }


    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }


    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public long getTelefono(){
        return telefono;
    }
    public void setTelefono(long telefono){
        this.telefono = telefono;
    }


    public long getNumeroidentidad(){
        return numeroidentidad;
    }
    public void setNumeroidentidad(long numeroidentidad){
        this.numeroidentidad = numeroidentidad;
    }


    public String getFechanacimiento(){
        return fechanacimiento;
    }
    public void setFechanacimiento(String fechanacimiento){
        this.fechanacimiento = fechanacimiento;
    }


    public String getCarrera(){
        return carrera;
    }
    public void setCarrera(String carrera){
        this.carrera = carrera;
    }
    //----------------------------------------------------------------------------------------------------------------------
}

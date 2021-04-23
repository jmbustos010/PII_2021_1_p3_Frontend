package model;


public class Bibliotecario{
    private long    id;
    private String  nombre;
    private long    telefono;
    private long    numeroidentidad;
    private String  fechanacimiento;
    private String  direccion;


    public Bibliotecario(){
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


    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    //------------------------------------------------------------------------------------------------------------------
}

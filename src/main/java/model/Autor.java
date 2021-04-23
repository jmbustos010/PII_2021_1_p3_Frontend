package model;


public class Autor {
    private long   id;
    private String nombre;
    private long   numeroidentidad;
    private String nacionalidad;
    private String generoescritura;
    private String fechanacimiento;


    public Autor(){
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


    public long getNumeroidentidad(){
        return numeroidentidad;
    }
    public void setNumeroidentidad(long numeroidentidad){
        this.numeroidentidad = numeroidentidad;
    }


    public String getNacionalidad(){
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }


    public String getGeneroescritura(){
        return generoescritura;
    }
    public void setGeneroescritura(String generoescritura){
        this.generoescritura = generoescritura;
    }


    public String getFechanacimiento(){
        return fechanacimiento;
    }
    public void setFechanacimiento(String fechanacimiento){
        this.fechanacimiento = fechanacimiento;
    }
    //------------------------------------------------------------------------------------------------------------------
}

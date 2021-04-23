package model;

public class Usuario {
    private long   id;
    private String nombre;
    private String apellido;
    private String nombreusuario;
    private String contrasenia;

    public Usuario(){
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


    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }


    public String getNombreusuario(){
        return nombreusuario;
    }
    public void setNombreusuario(String nombreusuario){
        this.nombreusuario = nombreusuario;
    }


    public String getContrasenia(){
        return contrasenia;
    }
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    //------------------------------------------------------------------------------------------------------------------
}

package model;

public class Editorial {

    private long    id;
    private String  nombre;
    private String  ubicacion;
    private String  correo;
    private long    telefono;
    private String  fechafundacion;

    public Editorial(){
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public long getTelefono() {
        return telefono;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public String getFechafundacion() {
        return fechafundacion;
    }
    public void setFechafundacion(String fechafundacion) {
        this.fechafundacion = fechafundacion;
    }
}

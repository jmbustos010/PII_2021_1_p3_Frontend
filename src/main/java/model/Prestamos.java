package model;

public class Prestamos {
    private long   id;
    private String nombrelibro;
    private String descripcion;
    private String condicion;
    private String fechasalida;
    private String fechaentrega;

    public Prestamos(){
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombrelibro() {
        return nombrelibro;
    }
    public void setNombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCondicion() {
        return condicion;
    }
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    public String getFechasalida() {
        return fechasalida;
    }
    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }
    public String getFechaentrega() {
        return fechaentrega;
    }
    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }
}

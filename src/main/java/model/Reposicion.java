package model;

public class Reposicion {
    private long   id;
    private String nombrealumno;
    private long   numerocuentaalumno;
    private String nombrelibro;
    private String motivo;
    private double precio;
    private String fechareposicion;

    public Reposicion() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombrealumno() {
        return nombrealumno;
    }
    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }
    public long getNumerocuentaalumno() {
        return numerocuentaalumno;
    }
    public void setNumerocuentaalumno(long numerocuentaalumno) {
        this.numerocuentaalumno = numerocuentaalumno;
    }
    public String getNombrelibro() {
        return nombrelibro;
    }
    public void setNombrelibro(String nombreLibro) {
        this.nombrelibro = nombreLibro;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getFechareposicion() {
        return fechareposicion;
    }
    public void setFechareposicion(String fechareposicion) {
        this.fechareposicion = fechareposicion;
    }
}

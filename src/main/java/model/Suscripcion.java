package model;

public class Suscripcion { private long id;
    private String tiposuscripcion;
    private String fechainicio;
    private String fechacierre;
    private String nombrealumno;
    private long   precio;

    public Suscripcion(){
    }
    public Suscripcion(long id, String tiposuscripcion, String fechainicio, String fechacierre, String nombrealumno, long precio){
        this.id              = id;
        this.tiposuscripcion = tiposuscripcion;
        this.fechainicio     = fechainicio;
        this.fechacierre     = fechacierre;
        this.nombrealumno    = nombrealumno;
        this.precio          = precio;
    }

    /////setters/////

    public void setId(long id) {
        this.id = id;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public void setTiposuscripcion(String tiposuscripcion) {
        this.tiposuscripcion = tiposuscripcion;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechacierre(String fechacierre) {
        this.fechacierre = fechacierre;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    //////getters/////

    public long getId() {
        return id;
    }

    public long getPrecio() {
        return precio;
    }

    public String getFechacierre() {
        return fechacierre;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public String getnombrealumno() {
        return nombrealumno;
    }

    public String gettiposuscripcion() {
        return tiposuscripcion;
    }
}

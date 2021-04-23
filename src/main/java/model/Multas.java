package model;

public class Multas {
    private long id;
    private String nombre;
    private String descripcion;
    private long numerocuenta;
    private double monto;
    private String fechacreacion;

    public Multas(){
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public long getId() {
        return id;
    }
    public void setId(long Id) {
        this.id = Id;
    }
    public long getNumerocuenta() {
        return numerocuenta;
    }
    public void setNumerocuenta(long numerocuenta) {
        this.numerocuenta = numerocuenta;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public String getFechacreacion() {
        return fechacreacion;
    }
    public void setFechacreacion(String fechaCreacion) {
        this.fechacreacion = fechaCreacion;
    }
}

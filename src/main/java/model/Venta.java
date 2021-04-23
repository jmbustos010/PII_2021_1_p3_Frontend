package model;

public class Venta {
    private long id;
    private String nombrelibro;
    private String fechaventa;
    private String nombrealumno;
    private long   idalumno;
    private long   preciolibro;

    public Venta(){

    }

    public Venta(long id, String nombrelibro, String fechaventa, String nombrealumno, long idalumno, long preciolibro){
        this.id = id;
        this.nombrelibro  = nombrelibro;
        this.fechaventa   = fechaventa;
        this.nombrealumno = nombrealumno;
        this.idalumno     = idalumno;
        this.preciolibro  = preciolibro;
    }

    /////setters/////
    public void setid(long id) {
        this.id = id;
    }

    public void setnombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }

    public void setfechaventa(String fechaventa) {
        this.fechaventa = fechaventa;
    }

    public void setnombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public void setidalumno(long idalumno) {
        this.idalumno = idalumno;
    }

    public void setpreciolibro(long preciolibro) {
        this.preciolibro = preciolibro;
    }

    ///////getters/////

    public long getid() {
        return id;
    }

    public String getnombrealumno() {
        return nombrealumno;
    }

    public long getidalumno() {
        return idalumno;
    }

    public long getpreciolibro() {
        return preciolibro;
    }

    public String getfechaventa() {
        return fechaventa;
    }

    public String getnombrelibro() {
        return nombrelibro;
    }
}

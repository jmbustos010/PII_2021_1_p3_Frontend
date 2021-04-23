package gui.principales;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;

public class frmDatos {
    public JPanel jpaPrincipal;
    private JScrollPane sclDatos;
    private JTable tblDatos;
    DefaultTableModel modelo;
    int x;


    public frmDatos(int x){
        opciones(x);
    }

    public void opciones(int x){
        switch (x){
            case 1:
                IniciarAlumnos();
                break;
            case 2:
                IniciarAutores();
                break;
            case 3:
                IniciarBibliotecarios();
                break;
            case 4:
                IniciarLibros();
                break;
            case 5:
                IniciarSuscripcion();
                break;
            case 6:
                IniciarTutor();
                break;
            case 7:
                IniciarTutorias();
                break;
            case 8:
                IniciarVenta();
                break;
            case 9:
                IniciarEditorial();
                break;
            case 10:
                IniciarMulta();
                break;
            case 11:
                IniciarPrestamo();
                break;
            case 12:
                IniciarReposicion();
                break;
        }
    }


    public void IniciarAlumnos(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Numero Identidad");
        modelo.addColumn("Fecha nacimiento");
        modelo.addColumn("Carrera");

        try{
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URLAlumno + "");

            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);

            List<Alumno> data = new Gson().fromJson(responseJson,new TypeToken<List<Alumno>>(){}.getType());


            switch (get.getStatus()) {
                case 200:
                    LeerDatosAlumnos(data);

                    break;

                default:
                    res = "Error";
                    break;
            }

        }catch(Exception e1){
            res = "error";
        }


    }
    //-----
    public void LeerDatosAlumnos(List<Alumno> alumnos){
        try {
            modelo.setRowCount(0);
            for (Alumno alumno1: alumnos){
                Object[] registrLeido = {alumno1.getId(), alumno1.getNombre(), alumno1.getTelefono(), alumno1.getNumeroidentidad(), alumno1.getFechanacimiento(), alumno1.getCarrera()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarAutores(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Numero Identidad");
        modelo.addColumn("Nacionalidad");
        modelo.addColumn("Genero Escritura");
        modelo.addColumn("Fecha nacimiento");


        try{
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URLAutor + "");

            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);

            List<Autor> data = new Gson().fromJson(responseJson,new TypeToken<List<Autor>>(){}.getType());


            switch (get.getStatus()) {
                case 200:
                    LeerDatosAutor(data);

                    break;

                default:
                    res = "Error";
                    break;
            }

        }catch(Exception e1){
            res = "error";
        }

    }
    //-----
    public void LeerDatosAutor(List<Autor> autores){
        try {
            modelo.setRowCount(0);
            for (Autor autor1: autores){
                Object[] registrLeido = {autor1.getId(), autor1.getNombre(), autor1.getNumeroidentidad(), autor1.getNacionalidad(), autor1.getGeneroescritura(), autor1.getFechanacimiento()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarBibliotecarios(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Numero Identidad");
        modelo.addColumn("Fecha nacimiento");
        modelo.addColumn("Direccion");



        try{
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URLBibliotecario + "");

            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);

            List<Bibliotecario> data = new Gson().fromJson(responseJson,new TypeToken<List<Bibliotecario>>(){}.getType());


            switch (get.getStatus()) {
                case 200:
                    LeerDatosBibliotecario(data);
                    break;

                default:
                    res = "Error";
                    break;
            }

        }catch(Exception e1){
            res = "error";
        }

    }
    //-----
    public void LeerDatosBibliotecario(List<Bibliotecario> bibliotecarios){
        try {
            modelo.setRowCount(0);
            for (Bibliotecario bibliotecario1: bibliotecarios){
                Object[] registrLeido = {bibliotecario1.getId(), bibliotecario1.getNombre(), bibliotecario1.getTelefono(), bibliotecario1.getNumeroidentidad(), bibliotecario1.getFechanacimiento(), bibliotecario1.getDireccion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarLibros(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Titulo");
        modelo.addColumn("Nombre Autor");
        modelo.addColumn("Genero");
        modelo.addColumn("Idioma");
        modelo.addColumn("Fecha Publicacion");



        try{
            Client client= ClientBuilder.newClient();

            WebTarget target = client.target(URLLibro + "");

            Invocation.Builder solicitud =target.request();

            Response get = solicitud.get();

            String responseJson = get.readEntity(String.class);

            List<Libro> data = new Gson().fromJson(responseJson,new TypeToken<List<Libro>>(){}.getType());


            switch (get.getStatus()) {
                case 200:
                    LeerDatosLibros(data);

                    break;

                default:
                    res = "Error";
                    break;
            }

        }catch(Exception e1){
            res = "error";
        }

    }
    //-----
    public void LeerDatosLibros(List<Libro> libros){
        try {
            modelo.setRowCount(0);
            for (Libro libro1: libros){
                Object[] registrLeido = {libro1.getId(), libro1.getTitulo(), libro1.getNombreautor(), libro1.getGenero(), libro1.getIdioma(), libro1.getFechapublicacion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarSuscripcion(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Tipo de suscripcion");
        modelo.addColumn("Fecha de inicio");
        modelo.addColumn("Fecha de cierre");
        modelo.addColumn("Nombre alumno");
        modelo.addColumn("Precio de suscripcion");

        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLSuscripcion + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Suscripcion> data = new Gson().fromJson(responseJson,new TypeToken<List<Suscripcion>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosSuscripcion(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosSuscripcion(List<Suscripcion> suscripciones){
        try {
            modelo.setRowCount(0);
            for (Suscripcion suscripcion: suscripciones){
                Object[] registrLeido = {suscripcion.getId(), suscripcion.gettiposuscripcion(), suscripcion.getFechainicio(), suscripcion.getFechacierre(), suscripcion.getnombrealumno(), suscripcion.getPrecio()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarTutor(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Numero Identidad");
        modelo.addColumn("Fecha nacimiento");
        modelo.addColumn("Carrera");

        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLTutor + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Tutor> data = new Gson().fromJson(responseJson,new TypeToken<List<Tutor>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosTutores(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosTutores(List<Tutor> tutores){
        try {
            modelo.setRowCount(0);
            for (Tutor tutor: tutores){
                Object[] registrLeido = {tutor.getId(), tutor.getNombre(), tutor.getTelefono(), tutor.getNumeroidentidad(), tutor.getFechanacimiento(), tutor.getclaseimpartida()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarTutorias(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre tutor");
        modelo.addColumn("Materia");
        modelo.addColumn("Horario");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Precio");

        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLTutorias + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Tutorias> data = new Gson().fromJson(responseJson,new TypeToken<List<Tutorias>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosTutorias(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosTutorias(List<Tutorias> tutorias){
        try {
            modelo.setRowCount(0);
            for (Tutorias tutorias1: tutorias){
                Object[] registrLeido = {tutorias1.getid(), tutorias1.getnombretutor(), tutorias1.getmateria(), tutorias1.gethorario(), tutorias1.getprecio(), tutorias1.getubicacion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarVenta(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre libro");
        modelo.addColumn("Fecha venta");
        modelo.addColumn("Nombre alumno");
        modelo.addColumn("Id alumno");
        modelo.addColumn("Precio libro");

        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLVenta + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Venta> data = new Gson().fromJson(responseJson,new TypeToken<List<Venta>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosVenta(data);

                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosVenta(List<Venta> ventas){
        try {
            modelo.setRowCount(0);
            for (Venta venta: ventas){
                Object[] registrLeido = {venta.getid(), venta.getnombrelibro(), venta.getnombrealumno(), venta.getfechaventa(), venta.getidalumno(), venta.getpreciolibro()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarEditorial(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ubicacion");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Fecha de Fundacion");
        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLEditorial + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Editorial> data = new Gson().fromJson(responseJson,new TypeToken<List<Editorial>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosEditoriales(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosEditoriales(List<Editorial> editoriales){
        try {
            modelo.setRowCount(0);
            for (Editorial editorial: editoriales){
                Object[] registrLeido = {editorial.getId(), editorial.getNombre(), editorial.getUbicacion(), editorial.getCorreo(), editorial.getTelefono(), editorial.getFechafundacion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarMulta(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Numero de Cuenta");
        modelo.addColumn("Monto");
        modelo.addColumn("Fecha de Creacion");
        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLMulta + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Multas> data = new Gson().fromJson(responseJson,new TypeToken<List<Multas>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosMulta(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //-----
    public void LeerDatosMulta(List<Multas> multas){
        try {
            modelo.setRowCount(0);
            for (Multas multa: multas){
                Object[] registrLeido = {multa.getId(), multa.getNombre(), multa.getDescripcion(), multa.getNumerocuenta(), multa.getMonto(), multa.getFechacreacion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarPrestamo(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre del libro");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Condicion");
        modelo.addColumn("Fecha de Salida");
        modelo.addColumn("Fecha de Entrega");

        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLPrestamo + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Prestamos> data = new Gson().fromJson(responseJson,new TypeToken<List<Prestamos>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosPrestamo(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //----------------
    public void LeerDatosPrestamo(List<Prestamos> prestamos){
        try {
            modelo.setRowCount(0);
            for (Prestamos prestamo: prestamos){
                Object[] registrLeido = {prestamo.getId(), prestamo.getNombrelibro(), prestamo.getDescripcion(), prestamo.getCondicion(), prestamo.getFechasalida(), prestamo.getFechaentrega()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    //-----
    public void IniciarReposicion(){
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre del Alumno");
        modelo.addColumn("Numero de Cuenta del Alumno");
        modelo.addColumn("Nombre del Libro");
        modelo.addColumn("Motivo");
        modelo.addColumn("Precio");
        modelo.addColumn("Fecha de Reposicion");
        try{
            Client client= ClientBuilder.newClient();
            WebTarget target = client.target(URLReposicion + "");
            Invocation.Builder solicitud =target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Reposicion> data = new Gson().fromJson(responseJson,new TypeToken<List<Reposicion>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDatosReposicion(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch(Exception e1){
            res = "Error, no se cual es";
        }
    }
    //----------------
    public void LeerDatosReposicion(List<Reposicion> reposicion){
        try {
            modelo.setRowCount(0);
            for (Reposicion reposiciones: reposicion){
                Object[] registrLeido = {reposiciones.getId(), reposiciones.getNombrealumno(), reposiciones.getNumerocuentaalumno(), reposiciones.getNombrelibro(), reposiciones.getMotivo(), reposiciones.getPrecio(), reposiciones.getFechareposicion()};
                modelo.addRow(registrLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    //-----
    String res = "";
    String URLAlumno        = "http://192.168.0.2:8080/api/v1/Alumno";
    String URLAutor         = "http://192.168.0.2:8080/api/v1/Autor";
    String URLBibliotecario = "http://192.168.0.2:8080/api/v1/Bibliotecario";
    String URLEditorial     = "http://192.168.0.2:8080/api/v1/Editorial";
    String URLLibro         = "http://192.168.0.2:8080/api/v1/Libro";
    String URLMulta         = "http://192.168.0.2:8080/api/v1/Multa";
    String URLPrestamo      = "http://192.168.0.2:8080/api/v1/Prestamo";
    String URLReposicion    = "http://192.168.0.2:8080/api/v1/Reposicion";
    String URLSuscripcion   = "http://192.168.0.2:8080/api/v1/Suscripcion";
    String URLTutor         = "http://192.168.0.2:8080/api/v1/Tutor";
    String URLTutorias      = "http://192.168.0.2:8080/api/v1/Tutoria";
    String URLVenta         = "http://192.168.0.2:8080/api/v1/Venta";
    //-----

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Datos");
        frame1.setContentPane(new frmDatos(frame1.getX()).jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Libro;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmLibros {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblLibros;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnlimpiar;
    private JButton btnBuscarPorTitulo;
    private JPanel jpaIzquierdo;
    private JLabel lblTitulo;
    private JLabel lblNombreAutor;
    private JLabel lblGenero;
    private JLabel lblIdioma;
    private JTextField txtTitulo;
    private JTextField txtNombreAutor;
    private JTextField txtGenero;
    private JTextField txtClasificaion;
    private JTextField txtIdioma;
    private JLabel lblFechaPublicaion;
    private JTextField txtFechaPublicaion;
    private JLabel lblId;
    private JTextField txtId;

    public frmLibros() {

        //-----------------------------------------------------------------------------------------------------Listeners
        //-----------------------------------------------------------------------------------------------------Listeners

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addLibro");

                    Invocation.Builder solicitud = target.request();

                    Libro libro1 = new Libro();
                    libro1.setId(Long.parseLong(txtId.getText()));
                    libro1.setTitulo(txtTitulo.getText());
                    libro1.setNombreautor(txtNombreAutor.getText());
                    libro1.setGenero(txtGenero.getText());
                    libro1.setIdioma(txtIdioma.getText());
                    libro1.setFechapublicacion(txtFechaPublicaion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(libro1);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Libro data = new Gson().fromJson(responseJson, Libro.class);
                            res = "El Libro "+data.getTitulo()+" se ha registrado con exito";
                            break;
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }

            }
        });
        //-----
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Libro libro1 = new Libro();
                    libro1.setId(Long.parseLong(txtId.getText()));
                    libro1.setTitulo(txtTitulo.getText());
                    libro1.setNombreautor(txtNombreAutor.getText());
                    libro1.setGenero(txtGenero.getText());
                    libro1.setIdioma(txtIdioma.getText());
                    libro1.setFechapublicacion(txtFechaPublicaion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(libro1);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Libro data = new Gson().fromJson(responseJson, Libro.class);
                            res = "El Libro con el ID "+data.getId()+" se ha actualizado con exito";
                            break;
                        case 404:
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }

            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);


                    switch (delete.getStatus()) {
                        case 200:
                            res = "El Libro se ha borrado con exito";
                            break;
                        case 404:
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }

            }
        });
        //-----
        btnBuscarPorId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Libro data = new Gson().fromJson(responseJson, Libro.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtTitulo.setText(data.getTitulo());
                            txtNombreAutor.setText(data.getNombreautor());
                            txtGenero.setText(data.getGenero());
                            txtIdioma.setText(data.getIdioma());
                            txtFechaPublicaion.setText(data.getFechapublicacion());

                            res = "Libro "+data.getId()+" se ha encontrado";
                            break;
                        case 404:
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }

            }
        });
        //-----
        btnBuscarPorTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/titulo/"+txtTitulo.getText());


                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Libro data = new Gson().fromJson(responseJson, Libro.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtTitulo.setText(data.getTitulo());
                            txtNombreAutor.setText(data.getNombreautor());
                            txtGenero.setText(data.getGenero());
                            txtIdioma.setText(data.getIdioma());
                            txtFechaPublicaion.setText(data.getFechapublicacion());

                            res = "Libro "+data.getTitulo()+" se ha encontrado";
                            break;
                        case 404:
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }

            }
        });
        //-----
        btnlimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtTitulo.setText("");
                txtNombreAutor.setText("");
                txtGenero.setText("");
                txtIdioma.setText("");
                txtFechaPublicaion.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos");
                frame1.setContentPane(new frmDatos(4).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Solo puede ingresa numeros en este campo");
                    e.consume();
                }
            }
        });
        txtFechaPublicaion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta(YYY-MM-DD)");
                    e.consume();
                }
            }
        });
    }


    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Libro";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Libros");
        frame1.setContentPane(new frmLibros().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

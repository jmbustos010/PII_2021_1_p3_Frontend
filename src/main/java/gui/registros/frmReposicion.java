package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Prestamos;
import model.Reposicion;
import model.RestApiError;


import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmReposicion {
    private JPanel jpaSuperior;
    private JLabel lblReposicion;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JPanel jpaIzquierdo;
    private JLabel lblNombreAlumno;
    private JLabel lblNombreLibro;
    private JLabel lblMotivo;
    private JLabel lblPrecio;
    private JTextField txtId;
    private JTextField txtNombreAlumno;
    private JTextField txtNombreLibro;
    private JTextField txtMotivo;
    private JTextField txtPrecio;
    private JLabel lblFechaReposicion;
    private JTextField txtFechaReposicion;
    private JLabel lblId;
    private JLabel lblNumeroCuenta;
    private JTextField txtNumeroCuenta;
    public JPanel jpaPrincipal;
    private JButton btnBuscarCuenta;

    public frmReposicion() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addReposicion");

                    Invocation.Builder solicitud = target.request();

                    Reposicion reposicion = new Reposicion();
                    reposicion.setId(Long.parseLong(txtId.getText()));
                    reposicion.setNombrealumno(txtNombreAlumno.getText());
                    reposicion.setNumerocuentaalumno(Long.parseLong(txtNumeroCuenta.getText()));
                    reposicion.setNombrelibro(txtNombreLibro.getText());
                    reposicion.setMotivo(txtMotivo.getText());
                    reposicion.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    reposicion.setFechareposicion(txtFechaReposicion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(reposicion);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Reposicion data = new Gson().fromJson(responseJson, Reposicion.class);
                            res = "Reposicion del libro con ID " + data.getId() + " agregado con exito!";
                            break;
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                } catch (Exception e1) {
                    res = e.toString();
                } finally {
                    JOptionPane.showMessageDialog(null, res);
                }
            }
        });
        //-----------------
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Reposicion reposicion = new Reposicion();
                    reposicion.setId(Long.parseLong(txtId.getText()));
                    reposicion.setNombrealumno(txtNombreAlumno.getText());
                    reposicion.setNumerocuentaalumno(Long.parseLong(txtNumeroCuenta.getText()));
                    reposicion.setNombrelibro(txtNombreLibro.getText());
                    reposicion.setMotivo(txtMotivo.getText());
                    reposicion.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    reposicion.setFechareposicion(txtFechaReposicion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(reposicion);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);

                    switch (put.getStatus()) {
                        case 200:
                            Reposicion data = new Gson().fromJson(responseJson, Reposicion.class);
                            res = "La reposicion con ID "+ data.getId() + "ha sido actualizada con exito";
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
        //-----------------
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
                            res = "La reposicion fue eliminada con exito";
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
        //-----------------
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/"+ txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Reposicion data = new Gson().fromJson(responseJson, Reposicion.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombreAlumno.setText(data.getNombrealumno());
                            txtNumeroCuenta.setText(String.valueOf(data.getNumerocuentaalumno()));
                            txtNombreLibro.setText(data.getNombrelibro());
                            txtMotivo.setText(data.getMotivo());
                            txtPrecio.setText(String.valueOf(data.getPrecio()));
                            txtFechaReposicion.setText(data.getFechareposicion());
                            res = "Reposicion " + data.getId() + " ha sido encontrada";
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
        //-----------------
        btnBuscarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/cuenta/"+txtNumeroCuenta.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Reposicion data = new Gson().fromJson(responseJson, Reposicion.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombreAlumno.setText(data.getNombrealumno());
                            txtNumeroCuenta.setText(String.valueOf(data.getNumerocuentaalumno()));
                            txtNombreLibro.setText(data.getNombrelibro());
                            txtMotivo.setText(data.getMotivo());
                            txtPrecio.setText(String.valueOf(data.getPrecio()));
                            txtFechaReposicion.setText(data.getFechareposicion());
                            res = "Reposicion " + data.getNumerocuentaalumno() + " ha sido encontrado";
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
        //-----------------
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(12).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
        //-----------------
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtNombreAlumno.setText("");
                txtNumeroCuenta.setText("");
                txtNombreLibro.setText("");
                txtMotivo.setText("");
                txtPrecio.setText("");
                txtFechaReposicion.setText("");
            }
        });
        //-----------------
        txtFechaReposicion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta");
                    e.consume();
                }
            }
        });
        //-----------------
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        //-----------------
        txtNumeroCuenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        //-----------------
        txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_COLON))){
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
    }
    //----------------------
    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Reposicion";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Reposicion");
        frame1.setContentPane(new frmReposicion().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

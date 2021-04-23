package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Prestamos;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmPrestamos {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblPrestamos;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JPanel jpaIzquierdo;
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblCondicion;
    private JLabel lblFechaSalida;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtCondicion;
    private JTextField txtFechaSalida;
    private JLabel lblFechaEntrega;
    private JTextField txtFechaEntrega;
    private JButton btnBuscarNombre;

    public frmPrestamos() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addPrestamo");

                    Invocation.Builder solicitud = target.request();

                    Prestamos prestamos = new Prestamos();
                    prestamos.setId(Long.parseLong(txtId.getText()));
                    prestamos.setNombrelibro(txtNombre.getText());
                    prestamos.setDescripcion(txtDescripcion.getText());
                    prestamos.setCondicion(txtCondicion.getText());
                    prestamos.setFechasalida(txtFechaSalida.getText());
                    prestamos.setFechaentrega(txtFechaEntrega.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(prestamos);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Prestamos data = new Gson().fromJson(responseJson, Prestamos.class);
                            res = "Prestamo del libro " + data.getNombrelibro() + " agregado con exito!";
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

                    Prestamos prestamos = new Prestamos();
                    prestamos.setId(Long.parseLong(txtId.getText()));
                    prestamos.setNombrelibro(txtNombre.getText());
                    prestamos.setDescripcion(txtDescripcion.getText());
                    prestamos.setCondicion(txtCondicion.getText());
                    prestamos.setFechasalida(txtFechaSalida.getText());
                    prestamos.setFechaentrega(txtFechaEntrega.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(prestamos);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);

                    switch (put.getStatus()) {
                        case 200:
                            Prestamos data = new Gson().fromJson(responseJson, Prestamos.class);
                            res = "El Prestamo con el ID " + data.getId() + " fue actualizado con exito";
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
                            res = "El prestamo fue eliminado con exito";
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
                            Prestamos data = new Gson().fromJson(responseJson, Prestamos.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombrelibro());
                            txtDescripcion.setText(data.getDescripcion());
                            txtCondicion.setText(data.getCondicion());
                            txtFechaSalida.setText(data.getFechasalida());
                            txtFechaEntrega.setText(data.getFechaentrega());
                            res = "Prestamo " + data.getId() + " ha sido encontrada";
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
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/name/"+txtNombre.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Prestamos data = new Gson().fromJson(responseJson, Prestamos.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombrelibro());
                            txtDescripcion.setText(data.getDescripcion());
                            txtCondicion.setText(data.getCondicion());
                            txtFechaSalida.setText(data.getFechasalida());
                            txtFechaEntrega.setText(data.getFechaentrega());
                            res = "Prestamo " + data.getNombrelibro() + " ha sido encontrada";
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
                frame1.setContentPane(new frmDatos(11).jpaPrincipal);
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
                txtNombre.setText("");
                txtDescripcion.setText("");
                txtCondicion.setText("");
                txtFechaSalida.setText("");
                txtFechaEntrega.setText("");
            }
        });
        //-----------------
        txtFechaSalida.addKeyListener(new KeyAdapter() {
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
        txtFechaEntrega.addKeyListener(new KeyAdapter() {
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

    }
//-------------------------
    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Prestamo";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Prestamos");
        frame1.setContentPane(new frmPrestamos().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

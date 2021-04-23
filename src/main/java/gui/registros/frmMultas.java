package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Multas;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmMultas {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblMultas;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JPanel jpaIzquierdo;
    private JLabel lblId;
    private JLabel lblNombreAlumno;
    private JLabel lblNumeroCuentaAlumno;
    private JLabel lblMontoDeMulta;
    private JLabel lblFechaCreacion;
    private JTextField txtId;
    private JTextField txtNombreAlumno;
    private JTextField txtNumeroCuentaAlumno;
    private JTextField txtMontoDeMulta;
    private JTextField txtFechaCreacion;
    private JLabel lblDescripcion;
    private JTextField txtDescripcion;
    private JButton btnBuscarNombre;

    public frmMultas() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addMulta");

                    Invocation.Builder solicitud = target.request();

                    Multas multas = new Multas();
                    multas.setId(Long.parseLong(txtId.getText()));
                    multas.setNombre(txtNombreAlumno.getText());
                    multas.setDescripcion(txtDescripcion.getText());
                    multas.setNumerocuenta(Long.parseLong(txtNumeroCuentaAlumno.getText()));
                    multas.setMonto(Double.parseDouble(txtMontoDeMulta.getText()));
                    multas.setFechacreacion(txtFechaCreacion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(multas);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Multas data = new Gson().fromJson(responseJson, Multas.class);
                            res = "Multa del alumno " + data.getNombre() + " agregado con exito!";
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
        //-------------------
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Multas multa = new Multas();
                    multa.setId(Long.parseLong(txtId.getText()));
                    multa.setNombre(txtNombreAlumno.getText());
                    multa.setDescripcion(txtDescripcion.getText());
                    multa.setNumerocuenta(Long.parseLong(txtNumeroCuentaAlumno.getText()));
                    multa.setMonto(Double.parseDouble(txtMontoDeMulta.getText()));
                    multa.setFechacreacion(txtFechaCreacion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(multa);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);

                    switch (put.getStatus()) {
                        case 200:
                            Multas data = new Gson().fromJson(responseJson, Multas.class);
                            res = "Multa con ID " + data.getId() + "ha sido actualizado con exito";
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
        //-------------------
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
                            res = "La multa fue eliminado";
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
        //-------------------
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
                            Multas data = new Gson().fromJson(responseJson, Multas.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombreAlumno.setText(data.getNombre());
                            txtDescripcion.setText(data.getDescripcion());
                            txtNumeroCuentaAlumno.setText(String.valueOf(data.getNumerocuenta()));
                            txtMontoDeMulta.setText(String.valueOf(data.getMonto()));
                            txtFechaCreacion.setText(data.getFechacreacion());
                            res = "Multa " + data.getId() + " ha sido encontrada";
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
        //-------------------
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/name/"+txtNombreAlumno.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Multas data = new Gson().fromJson(responseJson, Multas.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombreAlumno.setText(data.getNombre());
                            txtDescripcion.setText(data.getDescripcion());
                            txtNumeroCuentaAlumno.setText(String.valueOf(data.getNumerocuenta()));
                            txtMontoDeMulta.setText(String.valueOf(data.getMonto()));
                            txtFechaCreacion.setText(data.getFechacreacion());
                            res = "Multa " + data.getNombre() + " ha sido encontrada";
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
        //-------------------
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(10).jpaPrincipal);
                frame1.setResizable(false);;
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
        //-------------------
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtNombreAlumno.setText("");
                txtDescripcion.setText("");
                txtNumeroCuentaAlumno.setText("");
                txtMontoDeMulta.setText("");
                txtFechaCreacion.setText("");
            }
        });
        //-------------------
        txtFechaCreacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta");
                    e.consume();
                }
            }
        });
        //-------------------
        txtMontoDeMulta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        //-------------------
        txtNumeroCuentaAlumno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        //-------------------
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
//----------------------------
    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Multa";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Multas");
        frame1.setContentPane(new frmMultas().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

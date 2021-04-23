package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.RestApiError;
import model.Suscripcion;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;


public class frmSuscripcion {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblPeriodicos;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JPanel jpaIzquierdo;
    private JLabel lblTipoSub;
    private JLabel lblFechaInicio;
    private JLabel lblFechaCierre;
    private JTextField txtTipoSub;
    private JTextField txtFechaInicio;
    private JTextField txtFechaCierre;
    private JLabel lblIdSuscripcion;
    private JTextField txtid;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JTextField txtNombreAlumno;
    private JLabel lblNombreAlumno;
    private JButton btnBuscarPorNombre;
    private JTable tblDatos;
    DefaultTableModel modelo;

    public frmSuscripcion() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL+"/addSuscripcion");

                    Invocation.Builder solicitud = target.request();

                    Suscripcion suscripcion = new Suscripcion();
                    suscripcion.setId(Long.parseLong(txtid.getText()));
                    suscripcion.setTiposuscripcion(txtTipoSub.getText());
                    suscripcion.setFechainicio(txtFechaInicio.getText());
                    suscripcion.setFechacierre(txtFechaCierre.getText());
                    suscripcion.setNombrealumno(txtNombreAlumno.getText());
                    suscripcion.setPrecio(Long.parseLong(txtPrecio.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(suscripcion);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Suscripcion data = new Gson().fromJson(responseJson, Suscripcion.class);
                            res = "La suscripcion del " + data.getnombrealumno() + " ha sido agregado con exito!";
                            break;
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                } catch (Exception e) {
                    res = e.toString();
                } finally {
                    JOptionPane.showMessageDialog(null, res);
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud = target.request();

                    Suscripcion suscripcion = new Suscripcion();
                    suscripcion.setId(Long.parseLong(txtid.getText()));
                    suscripcion.setTiposuscripcion(txtTipoSub.getText());
                    suscripcion.setFechainicio(txtFechaInicio.getText());
                    suscripcion.setFechacierre(txtFechaCierre.getText());
                    suscripcion.setNombrealumno(txtNombreAlumno.getText());
                    suscripcion.setPrecio(Long.parseLong(txtPrecio.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(suscripcion);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Suscripcion data = new Gson().fromJson(responseJson, Suscripcion.class);
                            res = "La suscripcion con el ID "+data.getId()+" se ha actualizado con exito";
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
                } catch (Exception e) {
                    res = e.toString();
                } finally {
                    JOptionPane.showMessageDialog(null, res);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/" + txtid.getText());

                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);


                    switch (delete.getStatus()) {
                        case 200:
                            res = "La suscripcion se ha borrado con exito";
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
                }catch(Exception e){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });

        btnBuscarPorId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL+"/id/"+txtid.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Suscripcion data = new Gson().fromJson(responseJson, Suscripcion.class);
                            txtNombreAlumno.setText(data.getnombrealumno());
                            txtid.setText(String.valueOf(data.getId()));
                            txtFechaInicio.setText(data.getFechainicio());
                            txtFechaCierre.setText(data.getFechacierre());
                            txtPrecio.setText(String.valueOf(data.getPrecio()));
                            txtTipoSub.setText(data.gettiposuscripcion());

                            res = "Suscripcion "+data.getId()+" se ha encontrado";
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
                }catch(Exception e){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnBuscarPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/name/"+txtNombreAlumno.getText());

                    Invocation.Builder solicitud = target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Suscripcion data = new Gson().fromJson(responseJson, Suscripcion.class);
                            txtNombreAlumno.setText(data.getnombrealumno());
                            txtid.setText(String.valueOf(data.getId()));
                            txtFechaInicio.setText(data.getFechainicio());
                            txtFechaCierre.setText(data.getFechacierre());
                            txtPrecio.setText(String.valueOf(data.getPrecio()));
                            txtTipoSub.setText(data.gettiposuscripcion());

                            res = "Suscripcion de "+data.getnombrealumno()+" se ha encontrado";
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
                }catch(Exception e){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtNombreAlumno.setText("");
                txtid.setText("");
                txtFechaInicio.setText("");
                txtFechaCierre.setText("");
                txtPrecio.setText("");
                txtTipoSub.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(5).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            }
        });




        txtFechaInicio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c >='0') &&(c <='9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta (YYYY-MM-DD)");
                    e.consume();
                }
            }
        });
        txtFechaCierre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c >='0') &&(c <='9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta (YYYY-MM-DD)");
                    e.consume();
                }
            }
        });
        txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        txtid.addKeyListener(new KeyAdapter() {
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

    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Suscripcion";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Suscripciones");
        frame1.setContentPane(new frmSuscripcion().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

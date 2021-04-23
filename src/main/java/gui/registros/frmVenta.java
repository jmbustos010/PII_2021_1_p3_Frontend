package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.RestApiError;
import model.Venta;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmVenta {
    private JPanel jpaSuperior;
    private JLabel lblVentas;
    public JPanel jpaPrincipal;
    private JPanel jpaIzquierdo;
    private JLabel lblFechaDeVenta;
    private JLabel lblNombreLibro;
    private JLabel lblIdVenta;
    private JLabel lblNombreAlumno;
    private JLabel lblIdAlumno;
    private JLabel lblPrecioLibro;
    private JTextField txtfechadeventa;
    private JTextField txtnombrelibro;
    private JTextField txtid;
    private JTextField txtnombrealumno;
    private JTextField txtidalumno;
    private JTextField txtpreciolibro;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JButton btnBuscarPorIdAlumno;

    public frmVenta() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL+"/addVenta");

                    Invocation.Builder solicitud = target.request();

                    Venta venta = new Venta();
                    venta.setid(Long.parseLong(txtid.getText()));
                    venta.setnombrelibro(txtnombrelibro.getText());
                    venta.setfechaventa(txtfechadeventa.getText());
                    venta.setnombrealumno(txtnombrealumno.getText());
                    venta.setidalumno(Long.parseLong(txtidalumno.getText()));
                    venta.setpreciolibro(Long.parseLong(txtpreciolibro.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(venta);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Venta data = new Gson().fromJson(responseJson, Venta.class);
                            res = "La venta del libro " + data.getnombrelibro() + " ha sido agregado con exito!";
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

                    Venta venta = new Venta();
                    venta.setid(Long.parseLong(txtid.getText()));
                    venta.setnombrelibro(txtnombrelibro.getText());
                    venta.setfechaventa(txtfechadeventa.getText());
                    venta.setnombrealumno(txtnombrealumno.getText());
                    venta.setidalumno(Long.parseLong(txtidalumno.getText()));
                    venta.setpreciolibro(Long.parseLong(txtpreciolibro.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(venta);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Venta data = new Gson().fromJson(responseJson, Venta.class);
                            res = "La venta con ID " + data.getid() + " fue actualizada con exito";
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
                            res = "La venta con ha sido eliminado con exito";
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

                    WebTarget target = client.target(URL + "/id/"+txtid.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Venta data = new Gson().fromJson(responseJson, Venta.class);
                            txtid.setText(String.valueOf(data.getid()));
                            txtfechadeventa.setText(data.getfechaventa());
                            txtnombrelibro.setText(data.getnombrelibro());
                            txtnombrealumno.setText(data.getnombrealumno());
                            txtidalumno.setText(String.valueOf(data.getidalumno()));
                            txtpreciolibro.setText(String.valueOf(data.getpreciolibro()));
                            txtidalumno.setText(String.valueOf(data.getidalumno()));

                            res = "Venta "+data.getid()+" se ha encontrado";
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
        btnBuscarPorIdAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/alumnoid/"+txtidalumno.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Venta data = new Gson().fromJson(responseJson, Venta.class);
                            txtid.setText(String.valueOf(data.getid()));
                            txtfechadeventa.setText(data.getfechaventa());
                            txtnombrelibro.setText(data.getnombrelibro());
                            txtnombrealumno.setText(data.getnombrealumno());
                            txtidalumno.setText(String.valueOf(data.getidalumno()));
                            txtpreciolibro.setText(String.valueOf(data.getpreciolibro()));
                            txtidalumno.setText(String.valueOf(data.getidalumno()));

                            res = "Venta con Id de alumno "+data.getid()+" se ha encontrado";
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
                txtid.setText("");
                txtfechadeventa.setText("");
                txtnombrelibro.setText("");
                txtnombrealumno.setText("");
                txtidalumno.setText("");
                txtpreciolibro.setText("");
                txtidalumno.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(8).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            }
        });
        txtfechadeventa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c >='0') &&(c <='9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta (YYYY-MM-DD)");
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
        txtidalumno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_S))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        txtpreciolibro.addKeyListener(new KeyAdapter() {
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
    String URL = "http://192.168.0.2:8080/api/v1/Venta";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Ventas");
        frame1.setContentPane(new frmVenta().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

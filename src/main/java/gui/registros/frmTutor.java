package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.RestApiError;
import model.Tutor;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmTutor {
    private JPanel jpaSuperior;
    private JLabel lblTutores;
    private JPanel jpaIzquierdo;
    private JLabel lblNombre;
    private JLabel lblId;
    private JLabel lblFechaNacimiento;
    private JLabel lblNumeroIdentidad;
    private JLabel lblTelefono;
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtFechaNacimiento;
    private JTextField txtNumeroIdentidad;
    private JTextField txtTelefono;
    private JLabel lblClaseImpartida;
    private JTextField txtClaseImpartida;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JButton btnBuscarNombre;
    public JPanel jpaPrincipal;


    public frmTutor() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL+"/addTutor");

                    Invocation.Builder solicitud = target.request();

                    Tutor tutor = new Tutor();
                    tutor.setId(Long.parseLong(txtId.getText()));
                    tutor.setNombre(txtNombre.getText());
                    tutor.setFechanacimiento(txtFechaNacimiento.getText());
                    tutor.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    tutor.setTelefono(Long.parseLong(txtTelefono.getText()));
                    tutor.setClaseimpartida(txtClaseImpartida.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(tutor);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Tutor data = new Gson().fromJson(responseJson, Tutor.class);
                            res = "El tutor " + data.getNombre() + " ha sido agregado con exito!";
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

                    Tutor tutor = new Tutor();
                    tutor.setId(Long.parseLong(txtId.getText()));
                    tutor.setNombre(txtNombre.getText());
                    tutor.setFechanacimiento(txtFechaNacimiento.getText());
                    tutor.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    tutor.setTelefono(Long.parseLong(txtTelefono.getText()));
                    tutor.setClaseimpartida(txtClaseImpartida.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(tutor);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Tutor data = new Gson().fromJson(responseJson, Tutor.class);
                            res = "El tutor con el ID " + data.getId() + " ha sido actualizado con exito";
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

                    WebTarget target = client.target(URL + "/delete/" + txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);


                    switch (delete.getStatus()) {
                        case 200:
                            res = "El Tutor con fue eliminado con exito";
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
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/id/" + txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Tutor data = new Gson().fromJson(responseJson, Tutor.class);
                            txtNombre.setText(data.getNombre());
                            txtId.setText(String.valueOf(data.getId()));
                            txtClaseImpartida.setText(data.getclaseimpartida());
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
                            txtFechaNacimiento.setText(data.getFechanacimiento());

                            res = "Tutor "+data.getId()+" se ha encontrado";
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
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/name/" + txtNombre.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Tutor data = new Gson().fromJson(responseJson, Tutor.class);
                            txtNombre.setText(data.getNombre());
                            txtId.setText(String.valueOf(data.getId()));
                            txtClaseImpartida.setText(data.getclaseimpartida());
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
                            txtFechaNacimiento.setText(data.getFechanacimiento());

                            res = "Tutor "+data.getNombre()+" se ha encontrado";
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
                txtNombre.setText("");
                txtId.setText("");
                txtClaseImpartida.setText("");
                txtNumeroIdentidad.setText("");
                txtTelefono.setText("");
                txtFechaNacimiento.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(6).jpaPrincipal);
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
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        txtNumeroIdentidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar solo digitos");
                    e.consume();
                }
            }
        });
        txtFechaNacimiento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c >='0') &&(c <='9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresar una fecha correcta (YYYY-MM-DD)");
                    e.consume();
                }
            }
        });
    }

    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Tutor";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Tutores");
        frame1.setContentPane(new frmTutor().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

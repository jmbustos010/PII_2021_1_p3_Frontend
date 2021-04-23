package gui.registros;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.principales.frmDatos;
import model.Alumno;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.util.List;

public class frmAlumnos {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblAlumnos;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JPanel jpaIzquierdo;
    private JLabel lblNombre;
    private JLabel lblId;
    private JLabel lblFechaDeNacimiento;
    private JLabel lblNumeroIdentidad;
    private JLabel lblCarrera;
    private JLabel lblTelefono;
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtFechaDeNacimiento;
    private JTextField txtNumeroIdentidad;
    private JTextField txtCarrera;
    private JTextField txtTelefono;
    private JButton btnBuscarPorNombre;
    private JTable tblDatos;
    private JScrollPane sclDatos;


    public frmAlumnos() {
        //-----------------------------------------------------------------------------------------------------Listeners
        //-----------------------------------------------------------------------------------------------------Listeners
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addAlumno");

                    Invocation.Builder solicitud = target.request();

                    Alumno alumno1 = new Alumno();
                    alumno1.setId(Long.parseLong(txtId.getText()));
                    alumno1.setNombre(txtNombre.getText());
                    alumno1.setTelefono(Long.parseLong(txtTelefono.getText()));
                    alumno1.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    alumno1.setFechanacimiento(txtFechaDeNacimiento.getText());
                    alumno1.setCarrera(txtCarrera.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(alumno1);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Alumno data = new Gson().fromJson(responseJson, Alumno.class);
                            res = "El alumno "+data.getNombre()+" se ha registrado con exito";
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

                    Alumno alumno1 = new Alumno();
                    alumno1.setId(Long.parseLong(txtId.getText()));
                    alumno1.setNombre(txtNombre.getText());
                    alumno1.setTelefono(Long.parseLong(txtTelefono.getText()));
                    alumno1.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    alumno1.setFechanacimiento(txtFechaDeNacimiento.getText());
                    alumno1.setCarrera(txtCarrera.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(alumno1);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Alumno data = new Gson().fromJson(responseJson, Alumno.class);
                            res = "El Alumno con el ID "+data.getId()+" se ha actualizado con exito";
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
                            res = "El Alumno se ha borrado con exito";
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
        btnBuscarPorId.addActionListener(new ActionListener(){
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
                            Alumno data = new Gson().fromJson(responseJson, Alumno.class);

                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtTelefono.setText(String.valueOf((data.getTelefono())));
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtFechaDeNacimiento.setText(data.getFechanacimiento());
                            txtCarrera.setText(data.getCarrera());

                            res = "Alumno "+data.getId()+" se ha encontrado";
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
        btnBuscarPorNombre.addActionListener(new ActionListener() {
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
                            Alumno data = new Gson().fromJson(responseJson, Alumno.class);

                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtTelefono.setText(String.valueOf((data.getTelefono())));
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtFechaDeNacimiento.setText(data.getFechanacimiento());
                            txtCarrera.setText(data.getCarrera());

                            res = "Alumno "+data.getNombre()+" se ha encontrado";
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
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
                txtNumeroIdentidad.setText("");
                txtFechaDeNacimiento.setText("");
                txtCarrera.setText("");
            }
        });
        //-----
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos");
                frame1.setContentPane(new frmDatos(1).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            }
        });
        //-----
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

        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Solo puede ingresa numeros en este campo");
                    e.consume();
                }
            }
        });
        txtNumeroIdentidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    JOptionPane.showMessageDialog(null, "Solo puede ingresa numeros en este campo");
                    e.consume();
                }
            }
        });

        txtFechaDeNacimiento.addKeyListener(new KeyAdapter() {
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
    String URL = "http://192.168.0.2:8080/api/v1/Alumno";

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Alumnos");
        frame1.setContentPane(new frmAlumnos().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

    }
}

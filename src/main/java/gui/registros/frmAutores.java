package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Autor;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmAutores {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JLabel lblAutores;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JButton btnBuscarPorNombre;
    private JPanel jpaIzquierdo;
    private JLabel lblNombre;
    private JLabel lblId;
    private JLabel lblFechaDeNacimiento;
    private JLabel lblNumeroIdentidad;
    private JLabel lblNacionalidad;
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtFechaDeNacimiento;
    private JTextField txtNumeroIdentidad;
    private JTextField txtNacionalidad;
    private JLabel lblGeneroEscritura;
    private JTextField txtGeneroEscritura;

    public frmAutores() {
        //-----------------------------------------------------------------------------------------------------Listeners
        //-----------------------------------------------------------------------------------------------------Listeners
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addAutor");

                    Invocation.Builder solicitud = target.request();

                    Autor autor1 = new Autor();
                    autor1.setId(Long.parseLong(txtId.getText()));
                    autor1.setNombre(txtNombre.getText());
                    autor1.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    autor1.setNacionalidad(txtNacionalidad.getText());
                    autor1.setGeneroescritura(txtGeneroEscritura.getText());
                    autor1.setFechanacimiento(txtFechaDeNacimiento.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(autor1);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Autor data = new Gson().fromJson(responseJson, Autor.class);
                            res = "El Autor "+data.getNombre()+" se ha registrado con exito";
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

                    Autor autor1 = new Autor();
                    autor1.setId(Long.parseLong(txtId.getText()));
                    autor1.setNombre(txtNombre.getText());
                    autor1.setNumeroidentidad(Long.parseLong(txtNumeroIdentidad.getText()));
                    autor1.setNacionalidad(txtNacionalidad.getText());
                    autor1.setGeneroescritura(txtGeneroEscritura.getText());
                    autor1.setFechanacimiento(txtFechaDeNacimiento.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(autor1);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Autor data = new Gson().fromJson(responseJson, Autor.class);
                            res = "El autor con el ID "+data.getId()+" se ha actualizado con exito";
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
                            res = "El Autor se ha borrado con exito";
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
                            Autor data = new Gson().fromJson(responseJson, Autor.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtNacionalidad.setText(data.getNacionalidad());
                            txtGeneroEscritura.setText(data.getGeneroescritura());
                            txtFechaDeNacimiento.setText(data.getFechanacimiento());

                            res = "Autor "+data.getId()+" se ha encontrado";
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
                            Autor data = new Gson().fromJson(responseJson, Autor.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtNumeroIdentidad.setText(String.valueOf(data.getNumeroidentidad()));
                            txtNacionalidad.setText(data.getNacionalidad());
                            txtGeneroEscritura.setText(data.getGeneroescritura());
                            txtFechaDeNacimiento.setText(data.getFechanacimiento());

                            res = "Autor "+data.getNombre()+" se ha encontrado";
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
                txtNumeroIdentidad.setText("");
                txtNacionalidad.setText("");
                txtGeneroEscritura.setText("");
                txtFechaDeNacimiento.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos");
                frame1.setContentPane(new frmDatos(2).jpaPrincipal);
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
    String URL = "http://192.168.0.2:8080/api/v1/Autor";

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Autores");
        frame1.setContentPane(new frmAutores().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

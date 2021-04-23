package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.RestApiError;
import model.Tutorias;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmTutorias {
    private JPanel jpaSuperior;
    private JLabel lblTutorias;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarPorId;
    private JButton btnListar;
    private JButton btnLimpiar;
    private JButton btnBuscarPorMateria;
    private JPanel jpaIzquierdo;
    private JLabel lblNombreTutor;
    private JLabel lblMateria;
    private JLabel lblHorario;
    private JLabel lblUbicacion;
    private JLabel lblPrecio;
    private JTextField txtnombretutor;
    private JTextField txtmateria;
    private JTextField txthorario;
    private JTextField txtubicacion;
    private JTextField txtprecio;
    private JLabel lblId;
    private JTextField txtid;
    public JPanel jpaPrincipal;

    public frmTutorias() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL+"/addTutoria");

                    Invocation.Builder solicitud = target.request();

                    Tutorias tutorias = new Tutorias();
                    tutorias.setid(Long.parseLong(txtid.getText()));
                    tutorias.setnombretutor(txtnombretutor.getText());
                    tutorias.setmateria(txtmateria.getText());
                    tutorias.sethorario(txthorario.getText());
                    tutorias.setubicacion(txtubicacion.getText());
                    tutorias.setprecio(Long.parseLong(txtprecio.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(tutorias);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Tutorias data = new Gson().fromJson(responseJson, Tutorias.class);
                            res = "La tutoria impartida por " + data.getnombretutor() + " ha sido agregada con exito!";
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

                    Tutorias tutorias = new Tutorias();
                    tutorias.setid(Long.parseLong(txtid.getText()));
                    tutorias.setnombretutor(txtnombretutor.getText());
                    tutorias.setmateria(txtmateria.getText());
                    tutorias.sethorario(txthorario.getText());
                    tutorias.setubicacion(txtubicacion.getText());
                    tutorias.setprecio(Long.parseLong(txtprecio.getText()));

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(tutorias);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);


                    switch (put.getStatus()) {
                        case 200:
                            Tutorias data = new Gson().fromJson(responseJson, Tutorias.class);
                            res = "El tutor con ID " + data.getid() + " fue actualizado con exito";
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
                            res = "La tutoria fue eliminado con exito";
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

                    WebTarget target = client.target(URL + "/id/" + txtid.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Tutorias data = new Gson().fromJson(responseJson, Tutorias.class);
                            txtid.setText(String.valueOf(data.getid()));
                            txtnombretutor.setText(data.getnombretutor());
                            txtmateria.setText(data.getmateria());
                            txthorario.setText(data.gethorario());
                            txtprecio.setText(String.valueOf(data.getprecio()));
                            txtubicacion.setText(data.getubicacion());

                            res = "Tutoria "+data.getid()+" se ha encontrado";
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
        btnBuscarPorMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/name/" + txtmateria.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Tutorias data = new Gson().fromJson(responseJson, Tutorias.class);
                            txtid.setText(String.valueOf(data.getid()));
                            txtnombretutor.setText(data.getnombretutor());
                            txtmateria.setText(data.getmateria());
                            txthorario.setText(data.gethorario());
                            txtprecio.setText(String.valueOf(data.getprecio()));
                            txtubicacion.setText(data.getubicacion());

                            res = "Tutoria de "+data.getmateria()+" se ha encontrado";
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
                txtnombretutor.setText("");
                txtmateria.setText("");
                txthorario.setText("");
                txtprecio.setText("");
                txtubicacion.setText("");
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Datos registrados");
                frame1.setContentPane(new frmDatos(7).jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            }
        });
        txtprecio.addKeyListener(new KeyAdapter() {
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
    String URL = "http://192.168.0.2:8080/api/v1/Tutoria";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Tutorias");
        frame1.setContentPane(new frmTutorias().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

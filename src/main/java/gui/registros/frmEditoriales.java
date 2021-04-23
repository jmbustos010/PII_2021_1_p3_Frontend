package gui.registros;

import com.google.gson.Gson;
import gui.principales.frmDatos;
import model.Editorial;
import model.RestApiError;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class frmEditoriales {
    public JPanel jpaPrincipal;
    private JButton btnRegistrar;
    private JButton button3;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JPanel jpaSuperior;
    private JPanel jpaDerecho;
    private JLabel lblEditoriales;
    private JButton btnLimpiar;
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblUbicacion;
    private JLabel lblCorreo;
    private JLabel lblTelefono;
    private JLabel lblFechaFundacion;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtUbicacion;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtFechaFundacion;
    private JButton btnActualizar;
    private JPanel jpaIzquierdo;
    private JButton btnBuscarNombre;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public frmEditoriales() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addEditorial");

                    Invocation.Builder solicitud = target.request();

                    Editorial editorial = new Editorial();
                    editorial.setId(Long.parseLong(txtId.getText()));
                    editorial.setNombre(txtNombre.getText());
                    editorial.setUbicacion(txtUbicacion.getText());
                    editorial.setCorreo(txtCorreo.getText());
                    editorial.setTelefono(Long.parseLong(txtTelefono.getText()));
                    editorial.setFechafundacion(txtFechaFundacion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(editorial);

                    Response post = solicitud.post(Entity.json(jsonString));

                    String responseJson = post.readEntity(String.class);

                    switch (post.getStatus()) {
                        case 201:
                            Editorial data = new Gson().fromJson(responseJson, Editorial.class);
                            res = "Editorial " + data.getNombre() + " agregado con exito!";
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

                    Editorial editorial = new Editorial();
                    editorial.setId(Long.parseLong(txtId.getText()));
                    editorial.setNombre(txtNombre.getText());
                    editorial.setUbicacion(txtUbicacion.getText());
                    editorial.setCorreo(txtCorreo.getText());
                    editorial.setTelefono(Long.parseLong(txtTelefono.getText()));
                    editorial.setFechafundacion(txtFechaFundacion.getText());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(editorial);

                    Response put = solicitud.put(Entity.json(jsonString));

                    String responseJson = put.readEntity(String.class);

                    switch (put.getStatus()) {
                        case 200:
                            Editorial data = new Gson().fromJson(responseJson, Editorial.class);
                            res = "La Editorial con ID " + data.getId() + " fue actualizado con exito";
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
                            res = "Editorial fue eliminado con exito";
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

                    WebTarget target = client.target(URL + "/id/"+txtId.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Editorial data = new Gson().fromJson(responseJson, Editorial.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtUbicacion.setText(data.getUbicacion());
                            txtCorreo.setText(data.getCorreo());
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
                            txtFechaFundacion.setText(data.getFechafundacion());
                            res = "Editorial " + data.getId() + " ha sido encontrada";
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

                    WebTarget target = client.target(URL + "/name/"+txtNombre.getText());

                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);

                    switch (get.getStatus()) {
                        case 200:
                            Editorial data = new Gson().fromJson(responseJson, Editorial.class);
                            txtId.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getNombre());
                            txtUbicacion.setText(data.getUbicacion());
                            txtCorreo.setText(data.getCorreo());
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
                            txtFechaFundacion.setText(data.getFechafundacion());
                            res = "Editorial " + data.getNombre() + " ha sido encontrada";
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
                frame1.setContentPane(new frmDatos(9).jpaPrincipal);
                frame1.setResizable(false);
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
                txtNombre.setText("");
                txtUbicacion.setText("");
                txtCorreo.setText("");
                txtTelefono.setText("");
                txtFechaFundacion.setText("");
            }
        });
        //-------------------
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
        txtFechaFundacion.addKeyListener(new KeyAdapter() {
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
    String URL = "http://192.168.0.2:8080/api/v1/Editorial";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Editoriales");
        frame1.setContentPane(new frmEditoriales().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

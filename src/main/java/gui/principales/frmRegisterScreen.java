package gui.principales;

import com.google.gson.Gson;
import model.Alumno;
import model.RestApiError;
import model.Usuario;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRegisterScreen {
    public JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JPanel jpaInferior;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtNombreUsuario;
    private JPanel jpaMedio;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblNombreUsuario;
    private JLabel lblContrasenia;
    private JLabel lblRegistro;
    private JPasswordField txtContrasenia;
    private JLabel CustomImage;

    public frmRegisterScreen() {
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Login");
                frame1.setContentPane(new frmLoginScreen().jpaPrincipal);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/addUsuario");

                    Invocation.Builder solicitud = target.request();

                    Usuario usuario1 = new Usuario();
                    usuario1.setNombre(txtNombre.getText());
                    usuario1.setApellido(txtApellido.getText());
                    usuario1.setNombreusuario(txtNombreUsuario.getText());
                    usuario1.setContrasenia(txtContrasenia.getText());


                    Gson gson = new Gson();
                    String jsonString = gson.toJson(usuario1);

                    Response post = solicitud.post(Entity.json(jsonString));


                    String responseJson = post.readEntity(String.class);


                    switch (post.getStatus()) {
                        case 201:
                            Usuario data = new Gson().fromJson(responseJson, Usuario.class);
                            res = "El usuario "+data.getNombreusuario()+" se ha registrado con exito";
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
    }

    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Usuario";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Registro");
        frame1.setContentPane(new frmRegisterScreen().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

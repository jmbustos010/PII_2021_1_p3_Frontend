package gui.principales;

import com.google.gson.Gson;
import model.RestApiError;
import model.Usuario;

import javax.swing.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmLoginScreen {
    private JPanel jpaSuperior;
    private JPanel jpaInferior;
    private JButton btnRegistrar;
    private JButton btnIngresar;
    private JPanel jpaMedio;
    private JTextField txtNombreUsuario;
    public JPanel jpaPrincipal;
    private JLabel lblNombreUsuario;
    private JLabel lblContrasenia;
    private JLabel lblInicioSesion;
    private JPasswordField txtContrasenia;


    public frmLoginScreen() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame();
                frame2.setTitle("Registro");
                frame2.setContentPane(new frmRegisterScreen().jpaPrincipal);
                frame2.setResizable(false);
                frame2.pack();
                frame2.setLocationRelativeTo(null);
                frame2.setVisible(true);

            }
        });
        //-----
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/nombreusuario/"+txtNombreUsuario.getText());


                    Invocation.Builder solicitud =target.request();

                    Response get = solicitud.get();

                    String responseJson = get.readEntity(String.class);


                    switch (get.getStatus()) {
                        case 200:
                            Usuario data = new Gson().fromJson(responseJson, Usuario.class);
                            String contraseniaTemp = txtContrasenia.getText();

                            if (contraseniaTemp.equals(data.getContrasenia())){
                                res = "Inicio de sesion exitoso\n"+txtNombreUsuario.getText()+" Bienvenido";

                                JFrame frame1 = new JFrame();
                                frame1.setTitle("Menu Principal");
                                frame1.setContentPane(new frmMenuPrincipal().jpaPrincipal);
                                frame1.setResizable(false);
                                frame1.pack();
                                frame1.setLocationRelativeTo(null);
                                frame1.setVisible(true);

                            }else{
                                res = "Las credenciales son incorrectas";
                            }

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
    }

    String res = "";
    String URL = "http://192.168.0.2:8080/api/v1/Usuario";
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setTitle("Login");
        frame1.setContentPane(new frmLoginScreen().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}

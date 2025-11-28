package SistemaCine.Controlador;

import SistemaCine.Utilidades.Estilos;
import SistemaCine.Utilidades.LogicaGeneral;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import SistemaCine.Utilidades.LogicaLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author feder
 */
public class LoginViewController implements Initializable {
    
    @FXML private VBox root;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Hyperlink linkRegistro;
    @FXML private Button btnIniciarSesion;
    @FXML private Button btnSalir;
    @FXML private Label lblUsuario;
    @FXML private Label lblPass;
    
    LogicaGeneral lg = new LogicaGeneral();
    LogicaLogin ll = new LogicaLogin();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        
        Estilos.pintarTexto(lblUsuario, lblPass);
        Estilos.pintarTexto(lblUsuario, lblPass);
        Estilos.pintarBotonRojo(btnIniciarSesion);
        Estilos.pintarBotonGris(btnSalir);
        
        // 4. Pintar link (manual porque es un caso único)
        linkRegistro.setStyle("-fx-text-fill: #3498db;");
        txtUsuario.requestFocus();
    }    

    @FXML
    private void eventoIniciarSesion(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String pass = txtPassword.getText();
        
        if (ll.validarLoginEnBD(usuario, pass)) {
            lg.mostrarAlerta(Alert.AlertType.INFORMATION,"Bienvenido", "Ingreso exitoso");
            ll.abrirDashboard(usuario); // Método para cambiar de pantalla
            lg.cerrarVentanaActual(btnIniciarSesion);
        } else {
            lg.mostrarAlerta(Alert.AlertType.INFORMATION,"Error", "Usuario o contraseña incorrectos");
        }
    }

    @FXML
    private void eventoRegistrarse(ActionEvent event) {
        LogicaLogin ll = new LogicaLogin();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SistemaCine/Vista/RegistroView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            lg.cerrarVentanaActual(linkRegistro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eventoSalir(ActionEvent event) {
        btnSalir.setOnAction(e -> System.exit(0));
    }
}

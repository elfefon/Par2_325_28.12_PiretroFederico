package SistemaCine.Controlador;

import SistemaCine.Utilidades.Estilos; // <--- Importa tu clase
import SistemaCine.Utilidades.LogicaGeneral;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import SistemaCine.Persistencia.ConexionSQL;
import SistemaCine.Utilidades.Navegador;
import SistemaCine.Utilidades.Rutas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RegisterViewController implements Initializable {

    @FXML private VBox root;
    @FXML private Label lblPass;
    @FXML private Label lblNombre;
    @FXML private Label lblMail;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnRegistrar;
    @FXML private Button btnSalir;
    @FXML private Hyperlink linkLogin;
    LogicaGeneral lg = new LogicaGeneral();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        Estilos.pintarTexto(lblPass, lblNombre, lblMail);
        
        Estilos.pintarBotonRojo(btnRegistrar);
        Estilos.pintarBotonGris(btnSalir);
        
        linkLogin.setStyle("-fx-text-fill: #3498db;");
    }

    @FXML
    private void eventoRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String pass = txtPassword.getText();

        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            lg.mostrarAlerta(Alert.AlertType.WARNING, "Datos Incompletos", "Por favor completa todos los campos.");
            return; 
        }

        if (guardarEnBD(nombre, email, pass)) {
            lg.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario creado correctamente. Ahora inicia sesión.");
            Stage stageActual = Navegador.getStageDesdeNodo((Node) event.getSource());
            Navegador.cambiarVista(stageActual, Rutas.VISTA_LOGIN, "Iniciar Sesión");
        } else {
            lg.mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar. Quizás el email ya existe.");
        }
    }

    @FXML
    private void eventoIrALogin(ActionEvent event) {
        Navegador.irA(event, Rutas.VISTA_LOGIN, "Iniciar Sesión");
}
    @FXML
    private void eventoSalir(ActionEvent event) {
        System.exit(0);
    }

    
    private boolean guardarEnBD(String nombre, String email, String password) {
        String sql = "INSERT INTO clientes (nombre, email, password) VALUES (?, ?, ?)";
        
        try (Connection con = ConexionSQL.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, nombre); // primer ?
            pst.setString(2, email); // segundo ?
            pst.setString(3, password); // tercer ?
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error SQL al registrar: " + e.getMessage());
            return false;
        }
    }
    }    



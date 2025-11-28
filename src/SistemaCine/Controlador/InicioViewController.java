package SistemaCine.Controlador;

import SistemaCine.Utilidades.Estilos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author feder
 */
public class InicioViewController implements Initializable {
    
    @FXML private Button btnIniciarSesion;
    @FXML private Button btnRegistro;
    @FXML private Button btnSalir;
    @FXML private VBox root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        
        Estilos.pintarBotonRojo(btnIniciarSesion, btnRegistro); 
        Estilos.pintarBotonGris(btnSalir);

    }    
    
    @FXML
    private void eventoIniciarSesion(ActionEvent event) {
        abrirVentana("/SistemaCine/Vista/LoginView.fxml", "Login Cine UTN", btnIniciarSesion);
    }

    @FXML
    private void eventoRegistro(ActionEvent event) {
        abrirVentana("/SistemaCine/Vista/RegisterView.fxml", "Crear Cuenta", btnRegistro);
    }

    @FXML
    private void eventoSalir(ActionEvent event) {
        System.exit(0);
    }

    private void abrirVentana(String rutaFXML, String titulo, Button botonAncla) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            Stage ventanaActual = (Stage) botonAncla.getScene().getWindow();
            ventanaActual.close();

        } catch (Exception e) {
            System.out.println("Error al abrir la ventana: " + rutaFXML);
            e.printStackTrace();
        }
    }
}

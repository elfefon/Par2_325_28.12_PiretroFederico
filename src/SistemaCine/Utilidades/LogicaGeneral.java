package SistemaCine.Utilidades;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author feder
 */
public class LogicaGeneral {
    
    public void cerrarVentanaActual(Node nodo) {
        Stage stage = (Stage) nodo.getScene().getWindow();
        stage.close();
    }
     public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        }
}

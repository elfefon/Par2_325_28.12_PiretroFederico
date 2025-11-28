package SistemaCine.Utilidades;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image; 
import javafx.stage.Stage;

public class Navegador {

    public static void cambiarVista(Stage stage, String rutaFXML, String tituloVentana) {
        try {
            // carga el archivo usando la ruta que le pasemos
            Parent root = FXMLLoader.load(Navegador.class.getResource(rutaFXML));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setTitle(tituloVentana);
            
            try {
                stage.getIcons().add(new Image(Navegador.class.getResourceAsStream(Rutas.ICONO_APP)));
            } catch (Exception e) {
                // si no hay icono, no pasa nada
            }

            stage.centerOnScreen();
            stage.show();
            
        } catch (IOException e) {
            System.out.println("Error al navegar a: " + rutaFXML);
            e.printStackTrace();
        }
    }

    public static Stage getStageDesdeNodo(Node nodo) {
        return (Stage) nodo.getScene().getWindow();
    }
    
    // metodo simplificado para botones
    public static void irA(ActionEvent event, String rutaFXML, String titulo) {
        Node nodo = (Node) event.getSource();
        Stage stageActual = (Stage) nodo.getScene().getWindow();
        cambiarVista(stageActual, rutaFXML, titulo);
    }
}
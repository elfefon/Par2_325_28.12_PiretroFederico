package SistemaCine.Utilidades;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Estilos {

    // COLORES (Constantes)
    private static final String FONDO_OSCURO = "-fx-background-color: #141414;";
    private static final String TEXTO_BLANCO = "-fx-text-fill: #e5e5e5; -fx-font-size: 14px;";
    private static final String TITULO_GRANDE = "-fx-text-fill: #E50914; -fx-font-size: 24px; -fx-font-weight: bold;";
    
    private static final String BOTON_ROJO = "-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-cursor: hand;";
    private static final String BOTON_GRIS = "-fx-background-color: #333333; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand;";

    // Métodos para pintar cosas

    public static void pintarFondo(Region contenedor) {
        contenedor.setStyle(FONDO_OSCURO);
    }

    public static void pintarTitulo(Label label) {
        label.setStyle(TITULO_GRANDE);
    }

    public static void pintarTexto(Label... labels) {
        for (Label lbl : labels) {
            lbl.setStyle(TEXTO_BLANCO);
        }
    }

    public static void pintarBotonRojo(Button... botones) {
        for (Button btn : botones) {
            btn.setStyle(BOTON_ROJO);
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #ff0f1b; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;"));
            btn.setOnMouseExited(e -> btn.setStyle(BOTON_ROJO));
        }
    }

    public static void pintarBotonGris(Button... botones) {
        for (Button btn : botones) {
            btn.setStyle(BOTON_GRIS);
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #555555; -fx-text-fill: white; -fx-background-radius: 5;"));
            btn.setOnMouseExited(e -> btn.setStyle(BOTON_GRIS));
        }
    }
    
    public static void hacerTransparente(ScrollPane scroll, VBox contenido) {
        scroll.setStyle("-fx-background: #141414; -fx-background-color: transparent;");
        contenido.setStyle("-fx-background-color: transparent;");
    }
}
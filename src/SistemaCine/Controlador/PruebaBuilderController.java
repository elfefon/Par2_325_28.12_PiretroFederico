package SistemaCine.Controlador;

import SistemaCine.Utilidades.Estilos; 
import SistemaCine.Modelo.Pelicula;
import SistemaCine.Utilidades.Navegador;
import SistemaCine.Utilidades.Rutas;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author feder
 */
public class PruebaBuilderController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        contenedorPeliculas.setStyle("-fx-background-color: #141414;");
        scrollCarrusel.setStyle("-fx-background: #141414; -fx-background-color: #141414;");
        Estilos.pintarTexto(lblTitulo);
        
        cargarPeliculas();
        iniciarAnimacion();
    }  
    
    @FXML private Label lblTitulo;
    @FXML private Region root;
    @FXML private HBox contenedorPeliculas; 
    @FXML private ScrollPane scrollCarrusel;
    @FXML private MenuItem miHistorialEntradas;
    @FXML private MenuItem miVolver;
    
    @FXML
    
    private void irAMisEntradas(ActionEvent event) {
        try { 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SistemaCine/Vista/HistorialEntradas.fxml"));
            Parent root = loader.load();

            // Obtener el controlador para pasarle el usuario
            HistorialEntradasController controller = loader.getController();
            controller.setUsuario("UsuarioActual"); // Pásale el usuario real que tengas guardado

            Stage stage = (Stage) scrollCarrusel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void eventoVolver(ActionEvent event) {
        Stage stage = (Stage) scrollCarrusel.getScene().getWindow();
        Navegador.cambiarVista(stage, Rutas.VISTA_LOGIN, "Iniciar Sesión");
    }
    private void abrirSala(Pelicula peliSeleccionada) {
        System.out.println(">>> Click detectado en: " + peliSeleccionada.getTitulo());
        try {
            // cargar el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SistemaCine/Vista/SalaView.fxml"));
            Parent root = loader.load();

            // obtener controlador
            SalaViewController controladorSala = loader.getController();

            controladorSala.setDatos(peliSeleccionada, "UsuarioActual");

            // mostrar la ventana
            Stage stageActual = (Stage) scrollCarrusel.getScene().getWindow();

            // 2. CAMBIAMOS LA ESCENA EN LA MISMA VENTANA
            Scene scene = new Scene(root);

            // (Opcional) Si quieres mantener el estilo oscuro, agrega el CSS aquí también
            try {
                scene.getStylesheets().add(getClass().getResource("/estilos/cine.css").toExternalForm());
            } catch (Exception e) {}

            stageActual.setScene(scene);
            stageActual.setTitle("Sala: " + peliSeleccionada.getTitulo());
            stageActual.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void cargarPeliculas() {
        
        for (int i = 0; i < 2; i++) {
            Pelicula[] lista = {
                new Pelicula("Los vengadores - INFINITY WAR", "avengerInfinityWar.jpg"),
                new Pelicula("Batman - EL CABALLERO DE LA NOCHE","batmanElCaballeroDeLaNoche.jpg"),
                new Pelicula("El club de la pelea", "clubDeLaPelea.jpg"),
                new Pelicula("El Padrino", "elPadrino.jpg"),
                new Pelicula("El viaje de Chichiro", "elViajeDeChichiro.jpg"),
                new Pelicula("Forrest Gump", "forrestGump.jpg"),
                new Pelicula("Guardianes de la Galaxia", "guardianesDeLaGalaxia.jpg"),
                new Pelicula("Inception - EL ORIGEN","inceptionElOrigen.jpg"),
                new Pelicula("Interstellar","interstellar.jpg"),
                new Pelicula("La La Land","laLaLand.jpg"),
                new Pelicula("Pulp Fiction","pulpFiction.jpg"),
                new Pelicula("Shawshank Redemption","shawshankRedemption.jpg"),
                new Pelicula("Titanic","titanic.jpg"),
                new Pelicula("Wonder Woman","wonderWoman.jpg"),
                new Pelicula("Matrix", "matrix.jpg")
            };

            for (Pelicula peli : lista) {
                try {
                    String rutaImagen = "/imagenes/" + peli.getUrlImagen();
                    
                    Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));

                    ImageView visor = new ImageView(imagen);

                    visor.setFitHeight(250); // Altura fija
                    visor.setPreserveRatio(true); // Que no se deforme
                    
                    visor.setOnMouseClicked(e -> abrirSala(peli));

                    visor.setOnMouseEntered(e -> visor.setCursor(javafx.scene.Cursor.HAND));
                    visor.setOnMouseExited(e -> visor.setCursor(javafx.scene.Cursor.DEFAULT));
                    
                    contenedorPeliculas.getChildren().add(visor);

                } catch (Exception e) {
                    System.out.println("Error cargando imagen: " + e.getMessage());
                }
            }
        }
    }
    
   private void iniciarAnimacion() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(60), e -> {
        
        double actual = scrollCarrusel.getHvalue();
        
        double siguiente = actual + 0.002; 

        if (actual >= 0.5) { 
            scrollCarrusel.setHvalue(0.0);
        } else {
            scrollCarrusel.setHvalue(siguiente);
        }
    }));
    
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}
    
}


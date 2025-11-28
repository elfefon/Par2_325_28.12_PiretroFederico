package SistemaCine.Controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import SistemaCine.Persistencia.ConexionSQL;
import SistemaCine.Utilidades.Estilos;
import SistemaCine.Utilidades.Navegador;
import SistemaCine.Utilidades.Rutas;

public class HistorialEntradasController implements Initializable {

    // Solo necesitamos el contenedor y el botón. ¡Chau Tabla!
    @FXML private Region root;
    @FXML private VBox contenedorTickets; 
    @FXML private Button btnVolver;
    @FXML private Label lblTitulo;
    
    private String usuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        if (lblTitulo != null) lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: #E50914; -fx-font-weight: bold;");
        Estilos.pintarBotonRojo(btnVolver);
        
        contenedorTickets.setStyle("-fx-background-color: transparent;");
    }

    public void setUsuario(String usuario) {
        this.usuarioActual = usuario;
        cargarDatos();
    }

    private void cargarDatos() {
        contenedorTickets.getChildren().clear();

        String sql = "SELECT * FROM entradas WHERE usuario_email = ?";

        try (Connection con = ConexionSQL.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, usuarioActual);
            ResultSet rs = pst.executeQuery();

            boolean encontroAlgo = false;

            while (rs.next()) {
                encontroAlgo = true;
                String titulo = rs.getString("titulo_pelicula");
                int fila = rs.getInt("fila");
                int numero = rs.getInt("numero");

                VBox tarjetaVisual = crearDiseñoTicket(titulo, fila, numero);
                
                contenedorTickets.getChildren().add(tarjetaVisual);
            }
            
            if (!encontroAlgo) {
                Label lbl = new Label("No tienes entradas compradas.");
                lbl.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
                contenedorTickets.getChildren().add(lbl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private VBox crearDiseñoTicket(String peli, int fila, int asiento) {
        
        // La tarjeta amarilla
        VBox tarjeta = new VBox(10);
        tarjeta.setPadding(new Insets(15));

        // fondo amarillo, bordes redondos, sombra
        tarjeta.setStyle("-fx-background-color: #f1c40f; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
        tarjeta.setMaxWidth(Double.MAX_VALUE); // Que ocupe todo el ancho

        Label lblTitulo = new Label("🎬 " + peli);
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        // Renglón de abajo con los datos
        HBox datos = new HBox(20);
        datos.setAlignment(Pos.CENTER_LEFT);
        
        Label lblUbicacion = new Label("Fila " + fila + " - Asiento " + asiento);
        lblUbicacion.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        
        // Espaciador para empujar el precio a la derecha
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        
        Label lblPrecio = new Label("$5000");
        lblPrecio.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
        
        datos.getChildren().addAll(lblUbicacion, espacio, lblPrecio);

        tarjeta.getChildren().addAll(lblTitulo, new Separator(), datos);
        
        return tarjeta;
    }

    @FXML
    private void eventoVolver(ActionEvent event) {
        Navegador.irA(event, Rutas.VISTA_DASHBOARD, "Cine UTN");
    }
}



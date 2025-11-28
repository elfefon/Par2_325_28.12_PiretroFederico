package SistemaCine.Controlador;

import SistemaCine.Utilidades.Estilos; // <--- Importa tu clase
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime; // Para la fecha
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import SistemaCine.Modelo.Pelicula;
import SistemaCine.Persistencia.ConexionSQL;
import SistemaCine.Utilidades.Navegador;
import SistemaCine.Utilidades.Rutas;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;

public class SalaViewController implements Initializable {
    
    @FXML private Region root;
    @FXML private GridPane gridAsientos;
    @FXML private ImageView imgPoster;    
    @FXML private Button btnConfirmar;    
    @FXML private Label lblInfoAsiento;   
    @FXML private Label lblTituloPelicula;
    @FXML private MenuItem miVolver;
    
    private Pelicula peliculaActual;
    private String usuarioActual;
    
    private int filaSeleccionada = -1;
    private int numeroSeleccionado = -1;
    private Button botonSeleccionado = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (root != null) Estilos.pintarFondo(root);
        if (lblTituloPelicula != null) lblTituloPelicula.setStyle("-fx-font-size: 24px; -fx-text-fill: #E50914; -fx-font-weight: bold;");
        if (lblInfoAsiento != null) Estilos.pintarTexto(lblInfoAsiento);
        
        btnConfirmar.setDisable(true);
        Estilos.pintarBotonRojo(btnConfirmar);
        btnConfirmar.setDisable(true);
    }
    
    @FXML
    private void eventoVolver(ActionEvent event) {
        Navegador.irA(event, Rutas.VISTA_DASHBOARD, "Cine UTN");
    }
    
    
    public void setDatos(Pelicula peli, String usuario) {
        this.peliculaActual = peli;
        this.usuarioActual = usuario;
        
        if (lblTituloPelicula != null) {
        lblTituloPelicula.setText(peli.getTitulo()); 
        }
        // Carga la imagen a la izquierda
        try {
            String ruta = "/imagenes/" + peli.getUrlImagen();
            imgPoster.setImage(new Image(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el poster");
        }

        cargarButacas();
        
        // 2. Acción del botón Confirmar Compra
        btnConfirmar.setOnAction(e -> generarTicket());
    }

    private void cargarButacas() {
        gridAsientos.getChildren().clear();
        int filas = 5; 
        int columnas = 8;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Button btn = new Button("F" + i + "-" + j);
                btn.setPrefSize(50, 50);
                
                int f = i; int n = j;

                if (estaOcupada(peliculaActual.getTitulo(), f, n)) {
                    // OCUPADA (Rojo)
                    btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                    btn.setDisable(true);
                } else {
                    // LIBRE (Verde)
                    btn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
                    
                    // Acción al tocar una butaca libre: SELECCIONAR
                    btn.setOnAction(e -> seleccionarAsiento(f, n, btn));
                }
                gridAsientos.add(btn, j, i);
            }
        }
    }

    // Método para manejar la selección visual
    private void seleccionarAsiento(int fila, int numero, Button btn) {
        // Si ya había uno seleccionado antes, lo volvemos a poner verde
        if (botonSeleccionado != null) {
            botonSeleccionado.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        }

        // Guardamos la nueva selección
        this.filaSeleccionada = fila;
        this.numeroSeleccionado = numero;
        this.botonSeleccionado = btn;

        // Pintamos el seleccionado de AZUL para que se note
        btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        
        // Actualizamos el label de abajo
        lblInfoAsiento.setText("Seleccionado: Fila " + fila + " - Asiento " + numero + " ($5000)");
        
        // Habilitamos el botón de comprar
        btnConfirmar.setDisable(false);
    }

    // Ticket
    private void generarTicket() {
        guardarCompraEnBD();

        // generacion ticket
        Stage ticketStage = new Stage();
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f1c40f;"); // Fondo amarillo tipo ticket

        // datos ticket
        Label lblTitulo = new Label("--- TICKET DE CINE ---");
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        
        Label lblPelicula = new Label("Película: " + peliculaActual.getTitulo());
        Label lblAsiento = new Label("Ubicación: Fila " + filaSeleccionada + " - Nro " + numeroSeleccionado);
        Label lblPrecio = new Label("Precio: $5.000,00");
        Label lblUsuario = new Label("Cliente: " + usuarioActual);
        
        // fecha y hora 
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Label lblFecha = new Label("Fecha: " + ahora.format(formato));

        Label lblCodigo = new Label("ID Transacción: #" + System.currentTimeMillis() % 10000); // ID falso random

        Button btnCerrar = new Button("Imprimir y Cerrar");
        btnCerrar.setOnAction(e -> {
            ticketStage.close();
            // Recargamos las butacas para que la comprada aparezca roja
            cargarButacas(); 
            btnConfirmar.setDisable(true); // Desactivar botón de nuevo
            lblInfoAsiento.setText("Seleccione un asiento");
        });

        root.getChildren().addAll(lblTitulo, lblPelicula, lblAsiento, lblUsuario, lblFecha, lblPrecio, lblCodigo, btnCerrar);

        Scene scene = new Scene(root, 350, 450);
        ticketStage.setTitle("Tu Entrada");
        ticketStage.setScene(scene);
        ticketStage.show();
    }

    // metodos de BD:
    
    private boolean estaOcupada(String titulo, int f, int n) {
        try (Connection con = ConexionSQL.conectar()) {
            String sql = "SELECT * FROM entradas WHERE titulo_pelicula = ? AND fila = ? AND numero = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, titulo);
            pst.setInt(2, f);
            pst.setInt(3, n);
            return pst.executeQuery().next();
        } catch (Exception e) { return true; }
    }

    private void guardarCompraEnBD() {
        try (Connection con = ConexionSQL.conectar()) {
            String sql = "INSERT INTO entradas (titulo_pelicula, fila, numero, usuario_email) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, peliculaActual.getTitulo());
            pst.setInt(2, filaSeleccionada);
            pst.setInt(3, numeroSeleccionado);
            pst.setString(4, usuarioActual);
            pst.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
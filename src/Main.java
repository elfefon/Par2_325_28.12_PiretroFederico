import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
        
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SistemaCine/Vista/InicioView.fxml"));
            
            try {
                primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/imagenes/UTN .png")));
            }catch (Exception e) {
                System.out.println("No se encontró el icono");
            }
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Cine UTN");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println("Error al iniciar la app: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
    

package SistemaCine.Utilidades;

import SistemaCine.Persistencia.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogicaLogin {
    public void abrirDashboard(String usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SistemaCine/Vista/PruebaBuilder.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  
    public boolean validarLoginEnBD(String identificador, String password) {
        boolean existe = false;
        
        try (Connection con = ConexionSQL.conectar()) {
            if (con != null) {
                String sql = "SELECT * FROM clientes WHERE (email = ? OR nombre = ?) AND password = ?";
                
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, identificador); // comprueba mail
                pst.setString(2, identificador); // comprueba nombre
                pst.setString(3, password);      // comprueba contraseña
                
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    existe = true;
                    System.out.println("Entró el usuario: " + rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        
        return existe;
    }
   

}

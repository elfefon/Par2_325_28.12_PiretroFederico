package SistemaCine.Utilidades;

import java.sql.PreparedStatement;
import SistemaCine.Persistencia.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogicaRegistro {
        public void crearUsuario(String nombreIngresado, String mailIngresado,String contraseñaIngresada){
        try (Connection con = ConexionSQL.conectar()) {

            if (con != null) {

                String sql = "INSERT INTO clientes (nombre, email, password) VALUES (?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, nombreIngresado);   // Primer ?
                pst.setString(2, mailIngresado);    // Segundo ?
                pst.setString(3, contraseñaIngresada); // Tercer ?

                pst.executeUpdate();

                System.out.println("¡Cliente registrado exitosamente en la Base de Datos!");
        }
    }   catch (SQLException ex) {
            System.out.println("Error al guardar en la BD: " + ex.getMessage());
        }
        }
}

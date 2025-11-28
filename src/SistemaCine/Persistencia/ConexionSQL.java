package SistemaCine.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
 
    private static final String URL = "jdbc:mysql://localhost:3306/sistemacine"; 
    private static final String USER = "root"; 
    
    private static final String PASS = "3333"; 

    public static Connection conectar() {
        Connection con = null;
        try {
            // Esto carga el driver que agregaste a la librería
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intenta abrir la puerta
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(">> Conexión exitosa con la Base de Datos");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("!! Error de Conexión: " + e.getMessage());
        }
        return con;
    }
}

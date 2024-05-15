package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Adolf
 */
public class Conexion {

    private final String bd = "zapateria"; //nombre de la BD
    private final String user = "root"; //usuario con permisos para manejar la BD
    private final String password = "angel2000"; //contraseña asignada al usario root
    //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión MariaDB
    private final String url = "jdbc:mariadb://localhost:3306/" + bd;
    private Connection conector = null;

    public Connection Conectar() {
        try {
            //Driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            //Se inicia la conexión
            conector = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            //JOptionPane.showMessageDialog(null, "Conexion correcta");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la conexión a la base de datos:" + ex);
            //JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la conexión a la base de datos:" + ex);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la conexión a la base de datos:" + ex);
        }
        return conector;
    }

    public void cerrar() {
        try {
            if (conector != null) {
                conector.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /* Este main solo nos sirve para probar que ya se puede realizar la conexion*/
    public static void main(String[] args) {
        Conexion c1 = new Conexion();
        c1.Conectar();
    }

}
